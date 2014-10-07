package br.edu.ifpb.recdata.servicos;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.util.ItemAdapter;

public class BuscaItensServidor extends Activity {

	ListView listview ;
    ArrayList<Item> ListaItens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_view_item);

		
		ListaItens = new ArrayList<Item>();

		new Busca().execute();

	}

	private class Busca extends AsyncTask<Void, Integer, JSONObject> {

		private Intent intent ;
		private Bundle paramsBundle;
		
		public Busca() {

		}

		@Override
		protected JSONObject doInBackground(Void... params) {

			// recupera o valor da categoria de outra intent 
			intent = getIntent();
			paramsBundle = intent.getExtras();
			int categoriaIdBunble = paramsBundle.getInt("idcategoria");
			
			Log.i("IdCategoria ->", paramsBundle.toString());
			
		
			JSONObject jsonObjectEnvia = null;
			JSONObject jsonObjectRecebe = null;
			Item item =new Item();
				
			item.setIdCategoria(categoriaIdBunble);

			try {
				jsonObjectEnvia = new JSONObject(); 
				jsonObjectEnvia.put("descricaoCategoria",item.getIdItem() );
				jsonObjectEnvia.put("descricaoItem",item.getDescricaoItem());
				jsonObjectEnvia.put("idCategoria",item.getIdCategoria());
				jsonObjectEnvia.put("idItem",item.getIdItem() );
				
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			// Enviar a requisi��o HTTP via POST.
			HttpService httpService = new HttpService();
			HttpResponse response;
			try {
				response = httpService.sendJsonPostRequest("/item/leitor",jsonObjectEnvia);
				
				// Convers�o do response ( resposta HTTP) para String.
				String json = HttpUtil.entityToString(response);
				
				Log.i("AsyncTaskKJson", "Resquest - POST: " + json);

				try {

					jsonObjectRecebe = new JSONObject(json);
					JSONArray jsonArray = jsonObjectRecebe.getJSONArray("item");
					// itemAux serve para ser montado o objeto Item no lado cliente

					for (int i = 0; i < jsonArray.length(); i++) {
						Item itemAux = new Item();
						JSONObject explrObject = jsonArray.getJSONObject(i);
						itemAux.setDescricaoCategoria(explrObject
								.getString("descricaoCategoria"));
						itemAux.setDescricaoItem(explrObject
								.getString("descricaoItem"));
						itemAux.setIdCategoria(explrObject.getInt("idCategoria"));
						itemAux.setIdItem(explrObject.getInt("idItem"));

						ListaItens.add(itemAux);
					}

					
				} catch (JSONException e) {

					Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
					
				}

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
			jsonObjectEnvia = null;

			
			return jsonObjectRecebe;

		
		}

		@Override
		protected void onPostExecute(JSONObject result) {

			listview = (ListView) findViewById(R.id.listaResultados);	
			listview.setAdapter(new ItemAdapter(BuscaItensServidor.this,ListaItens));
			super.onPostExecute(result);

		}
	}
}