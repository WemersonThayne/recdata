package br.edu.ifpb.recdata.telas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.edu.ifpb.R;
import br.edu.ifpb.recdata.entity.Item;
import br.edu.ifpb.recdata.entity.ReservaItem;
import br.edu.ifpb.recdata.listener.DataFimReservaListener;
import br.edu.ifpb.recdata.listener.DataInicioReservaListener;
import br.edu.ifpb.recdata.listener.HoraFimReservaListener;
import br.edu.ifpb.recdata.listener.HoraInicioReservaListener;
import br.edu.ifpb.recdata.servicos.ReservarAsyncTask;
import br.edu.ifpb.recdata.util.DatePickerDialogAdapter;
import br.edu.ifpb.recdata.util.GlobalState;
import br.edu.ifpb.recdata.util.TimePickerDialogAdapter;

public class TelaReservar extends Activity implements OnClickListener {

	Item itemBundle = null;
	ReservaItem reserva = null;

	private EditText dataInicioEditText;
	private DatePickerDialog dataInicioPickerDialog;

	private EditText horaInicioEditText;
	private TimePickerDialog horaInicioPickerDialog;

	private EditText dataFimEditText;
	private DatePickerDialog dataFimPickerDialog;

	private EditText horaFimEditText;
	private TimePickerDialog horaFimPickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserva_item);
		getViews();
		
		setDateInicioPickerDialog();
		setHoraInicioPickerDialog();
		
		setDateFimPickerDialog();
		setHoraFimPickerDialog();

		Intent intent = getIntent();
		Bundle paramsBundle = intent.getExtras();

		itemBundle = (Item) paramsBundle.getSerializable("Item");

		Log.i("RecDATA", "Item  ->" + paramsBundle.toString());

		Button buscabutton = (Button) findViewById(R.id.buttonReserva);
		buscabutton.setOnClickListener(this);
		
		// Voltar intent.
		Button voltaListaItens = (Button) findViewById(R.id.buttonVoltarLista);		
		voltaListaItens.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent chamarTelaQr = new Intent(TelaReservar.this,
						TelaListaFuncionalidades.class);
				startActivity(chamarTelaQr);
				finish();
			}
		});
	}

	public void getViews() {
		this.dataInicioEditText = (EditText) findViewById(R.id.dataInicio);
		this.horaInicioEditText = (EditText) findViewById(R.id.horaInicio);
		this.dataFimEditText = (EditText) findViewById(R.id.dataFim);
		this.horaFimEditText = (EditText) findViewById(R.id.horaFim);
	}

	private JSONObject montarJsonReserva() {

		JSONObject reservaItemJson = null;
		JSONObject idUsuarioJson = null;
		JSONObject idItemJson = null;

		try {

			GlobalState gs = (GlobalState) getApplication();

			reservaItemJson = new JSONObject();

			idUsuarioJson = new JSONObject();
			idUsuarioJson.put("id", gs.getUsuario().getId());
			reservaItemJson.put("usuarioReserva", idUsuarioJson);
			reservaItemJson.put("usuarioAtendente", idUsuarioJson);

			idItemJson = new JSONObject();
			idItemJson.put("id", itemBundle.getId());
			reservaItemJson.put("item", idItemJson);

			// TODO: Colocar o campo texto para inserir observa��o.
			reservaItemJson.put("horaDataInicio",
					getDataHora(dataInicioEditText, horaInicioEditText));
			reservaItemJson.put("horaDataFim", 
					getDataHora(dataFimEditText, horaFimEditText));

			Log.i("RecDATA - ReservaJSON", reservaItemJson.toString());

		} catch (JSONException e) {

			Log.e("RecDATA", e.getMessage());
		}

		return reservaItemJson;
	}

	@Override
	public void onClick(View arg) {
		// TODO: Colocar valida��o dos campos
		ReservarAsyncTask reservarAsyncTask = new ReservarAsyncTask(this);
		JSONObject jsonObsect = montarJsonReserva();
		reservarAsyncTask.execute(jsonObsect);
	}

	public EditText getEditTextDataInicio() {
		return dataInicioEditText;
	}

	public String getValorEditTextDataInicio() {
		return dataInicioEditText.getText().toString();
	}

	public DatePickerDialog getDataInicioPickerDialog() {
		return dataInicioPickerDialog;
	}

	private void setDateInicioPickerDialog() {
		DataInicioReservaListener listener = new DataInicioReservaListener(this);

		dataInicioEditText.setOnClickListener(listener);

		DatePickerDialogAdapter dataInicoDatePicker = new DatePickerDialogAdapter(
				this, dataInicioEditText);
		dataInicioPickerDialog = dataInicoDatePicker.builder();
	}

	public EditText getEditTextHoraInicio() {
		return horaInicioEditText;
	}

	public String getValorEditTextHoraInicio() {
		return horaInicioEditText.getText().toString();
	}

	public TimePickerDialog getHoraInicioPickerDialog() {
		return horaInicioPickerDialog;
	}

	private void setHoraInicioPickerDialog() {
		
		HoraInicioReservaListener listener = new HoraInicioReservaListener(this);

		horaInicioEditText.setOnClickListener(listener);

		TimePickerDialogAdapter timeInicoDatePicker = new TimePickerDialogAdapter(
				this, horaInicioEditText);
		horaInicioPickerDialog = timeInicoDatePicker.builder();
	}

	// M�todos para a Captura da Data Final da Reserva
	public EditText getEditTextDataFim() {
		return dataFimEditText;
	}

	public String getValorEditTextDataFim() {
		return dataFimEditText.getText().toString();
	}

	public DatePickerDialog getDataFimPickerDialog() {
		return dataFimPickerDialog;
	}

	private void setDateFimPickerDialog() {
		DataFimReservaListener listener = new DataFimReservaListener(this);

		dataFimEditText.setOnClickListener(listener);

		DatePickerDialogAdapter dataFimDatePicker = new DatePickerDialogAdapter(
				this, dataFimEditText);
		dataFimPickerDialog = dataFimDatePicker.builder();
	}

	// M�todos para a Captura da hora Final
	public EditText getEditTextHoraFim() {
		return horaFimEditText;
	}

	public String getValorEditTextHoraFim() {
		return horaFimEditText.getText().toString();
	}

	public TimePickerDialog getHoraFimPickerDialog() {
		return horaFimPickerDialog;
	}

	private void setHoraFimPickerDialog() {
		HoraFimReservaListener listener = new HoraFimReservaListener(this);

		horaFimEditText.setOnClickListener(listener);

		TimePickerDialogAdapter timeFimDatePicker = new TimePickerDialogAdapter(
				this, horaFimEditText);
		horaFimPickerDialog = timeFimDatePicker.builder();
	}

	
	private Date getDataHora(EditText data, EditText hora) {
		
		String dateInString = data.getText().toString() + " "
				+ hora.getText().toString();

		String dataBanco;
		
		Date date=null;
		//Calendar calendar = Calendar.getInstance();

		try {
			date = new SimpleDateFormat(
					"yyyy-mm-dd hh:mm:ss").parse(dateInString);
			
			dataBanco=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
			Log.i("Date Parce",dataBanco.toString());
			date= new SimpleDateFormat().parse(dataBanco);
			//calendar.setTime(date);

			Log.i("Date Final",date.toString());
			//Log.i("Calendar Final",calendar.toString());
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.i("Data Formato", dateInString.toString());

		return date;
	}
}