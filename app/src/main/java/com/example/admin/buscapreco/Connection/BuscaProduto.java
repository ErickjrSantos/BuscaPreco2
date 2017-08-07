package com.example.admin.buscapreco.Connection;

import android.os.AsyncTask;

import com.example.admin.buscapreco.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 07/08/2017.
 */

public class BuscaProduto extends AsyncTask {

    String url = "http://187.35.128.157:70/BuscaPreco/buscaProduto.php";
    @Override
    protected Object doInBackground(Object[] objects) {

        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");


            //dados POST
            String urlParameters = "codb=" + objects[0];


            //Cria POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            int responseCode = con.getResponseCode();
            System.out.println("Codigo de resposta: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String JsonStr = response.toString();
            if(JsonStr != null || JsonStr != ""){
                try{
                    Produto produto = new Produto();
                    JSONObject jsonObjt = new JSONObject(JsonStr);
                    boolean existeProduto = jsonObjt.getString("response").equals("true");


                    produto.setResponse(existeProduto);

                    if(produto.getResponse()) {
                        JSONObject JsonProduto = jsonObjt.getJSONObject("produto");
                        String nomeProduto = JsonProduto.getString("nome");
                        String preco = JsonProduto.getString("preco");
                        String codB = JsonProduto.getString("codigo");



                        produto.setPreco(preco);
                        produto.setNomeProduto(nomeProduto);

                        produto.setCod(codB);

                    }
                    return produto;

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
}
