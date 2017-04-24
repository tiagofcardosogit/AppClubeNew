package br.com.opet.tds.appclube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 03/04/2017.
 */

public class Util {
    /**
     *Lê um arquivo da web via HTTP e converte o mesmo em String.
     *@param inputStream Stream do arquivo local no aplicativo
     *@return O arquivo convertido em String.
     */
    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static String convertClubetoJSON(Clube clube){
        JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("nome_clube",clube.getNome());
            mainObject.put("cidade_clube",clube.getCidade());
            mainObject.put("ano_clube",clube.getAno());

            return mainObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Clube> convertJSONtoClube(String jsonFile){
        List<Clube> clubes = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(jsonFile);

            for(int i = 0; i < mainObject.length(); i++){
                Clube novoClube = new Clube();
                JSONObject localObj = mainObject.getJSONObject(i);
                long id = localObj.getLong("id_clube");
                String nome = localObj.getString("nome_clube");
                String cidade = localObj.getString("cidade_clube");
                int ano = localObj.getInt("ano_clube");
                novoClube.setID(id);
                novoClube.setNome(nome);
                novoClube.setCidade(cidade);
                novoClube.setAno(ano);
                clubes.add(novoClube);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clubes;
    }

    public static String getStatusFromJSON(String json){
        try {
            return new JSONObject(json).getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
