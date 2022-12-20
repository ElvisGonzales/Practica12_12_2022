package com.example.practica12_12_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.practica12_12_2022.WebServices.Asynchtask;
import com.example.practica12_12_2022.WebServices.WebService;
import com.example.practica12_12_2022.adactadores.AdactadorUsuarios;
import com.example.practica12_12_2022.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    ListView lstusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstusuario = (ListView) findViewById(R.id.lstusuario);
        //View header = getLayoutInflater().inflate(R.layout.ly_header, null);
        //lstOpciones.addHeaderView(header);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista =  new JSONObject(result);
        JSONArray JSONlistaUsuarios=  JSONlista.getJSONArray("data");
        ArrayList<Usuario> lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);
        AdactadorUsuarios adapatorUsuario = new AdactadorUsuarios(this, lstUsuarios);
        lstusuario.setAdapter(adapatorUsuario);

    }
}