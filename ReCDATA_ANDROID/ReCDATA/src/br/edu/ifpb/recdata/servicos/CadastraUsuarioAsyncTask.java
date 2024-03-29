package br.edu.ifpb.recdata.servicos;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.recdata.telas.TelaLogin;
import br.edu.ifpb.recdata.util.Constantes;

public class CadastraUsuarioAsyncTask extends
		AsyncTask<JSONObject, Void, HttpResponse> {

	Activity activity;

	public CadastraUsuarioAsyncTask(Activity activity) {

		this.activity = activity;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected HttpResponse doInBackground(JSONObject... jsonObjects) {
		// Enviar a requisi��o HTTP via GET.
		HttpResponse response = HttpService.sendJsonPostRequest(
				"/usuario/cadastrar", jsonObjects[0]);
		return response;
	}

	@Override
	protected void onPostExecute(HttpResponse response) {

		int httpCode = response.getStatusLine().getStatusCode();

		try {
			// Convers�o do response ( resposta HTTP) para String.
			String json = HttpUtil.entityToString(response);
			Log.i("ReCDATA", "Resquest - POST: " + json);

			JSONObject jsonObject = new JSONObject(json);

			if (httpCode > 200 && httpCode < 400) {

				Toast.makeText(activity.getApplicationContext(),
						Constantes.USUARIO_CADASTRADO, Toast.LENGTH_SHORT)
						.show();

				Intent intent = new Intent(activity, TelaLogin.class);
				activity.startActivity(intent);
				activity.finish();

			} else {
				//TODO: criar uma dialog
				Toast.makeText(activity.getApplicationContext(),
						jsonObject.getString("ERRO Ao Cadastra! "), //Confuso!
						Toast.LENGTH_SHORT).show();
			}

		} catch (JSONException e) {
			Log.e("ReCDATA", "Error parsing data " + e.toString());
		}

	}
}
