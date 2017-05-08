package br.com.opet.tds.appclube;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsertClubeActivity extends Activity {

    private EditText editNomeClube;
    private EditText editCidadeClube;
    private EditText editAnoClube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_clube);
        editNomeClube = (EditText) findViewById(R.id.editNomeClube);
        editCidadeClube = (EditText) findViewById(R.id.editCidadeClube);
        editAnoClube = (EditText) findViewById(R.id.editAnoClube);
    }

    public void cadastrarClube(View v){
        Clube clube = new Clube();
        clube.setNome(editNomeClube.getText().toString());
        clube.setCidade(editCidadeClube.getText().toString());
        clube.setAno(Integer.parseInt(editAnoClube.getText().toString()));
        if(isConnected())
            new UploadToMyAPI().execute(clube);
        else
            Toast.makeText(this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
    }

    private boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private class UploadToMyAPI extends AsyncTask<Clube, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Clube... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://webtests.pe.hu/insert.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = Util.convertClubetoJSON(params[0]);
                outputStream.writeBytes(result);

                serverResponseCode = urlConnection.getResponseCode();
                serverResponseMessage = Util.webToString(urlConnection.getInputStream());

                outputStream.flush();
                outputStream.close();

                return result;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent listaClube = null;
            if(Util.getStatusFromJSON(serverResponseMessage).equals("1")) {
                Toast.makeText(InsertClubeActivity.this, "Clube registrado no Sistema!", Toast.LENGTH_SHORT).show();
                listaClube = new Intent(InsertClubeActivity.this, ListClubeActivity.class);
                startActivity(listaClube);
            }else{
                Toast.makeText(InsertClubeActivity.this, "Falha ao cadastrar o clube.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
