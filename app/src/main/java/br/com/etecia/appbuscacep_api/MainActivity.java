package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView lblCEP, lblLogradouro, lblComplemento, lblBairro, lblCidade, lblEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        lblCEP = findViewById(R.id.btnBuscaCep);
        lblLogradouro = findViewById(R.id.etxLogra);
        lblComplemento = findViewById(R.id.etxComp);
        lblBairro = findViewById(R.id.etxBairro);
        lblCidade = findViewById(R.id.etxCidade);
        lblEstado = findViewById(R.id.etxEstado);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCEP.setText(retorno.getCep().toString());
                    lblLogradouro.setText(retorno.getLogradouro().toString());
                    lblComplemento.setText(retorno.getComplemento().toString());
                    lblBairro.setText(retorno.getBairro().toString());
                    lblCidade.setText(retorno.getLocalidade().toString());
                    lblEstado.setText(retorno.getUf().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}