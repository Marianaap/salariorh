package com.example.salario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText salario;
    private EditText dep;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        salario = (EditText) findViewById(R.id.salarioedit);
        dep = (EditText) findViewById(R.id.dependentesedit);

    }

    public void calcular(View view) {


        if(salario.getText().toString().isEmpty() || dep.getText().toString().isEmpty() ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setMessage("Preencha todos os campos!");


            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alerta = builder.create();
            alerta.show();
        }else {

            double salariobruto = Double.parseDouble(salario.getText().toString());
            double dependentes = Double.parseDouble(dep.getText().toString());
            double INSS = 0;
            double salariobase = 0;
            double IRRF = 0;
            double valorliquido = 0;

        if (salariobruto <= 1045.00) {
            INSS = salariobruto * 0.075;
        } else if (salariobruto >= 1045.01 & salariobruto <= 2089.60) {
            INSS = salariobruto * 0.09 - 16.65;
        } else if (salariobruto >= 2089.61 & salariobruto <= 3134.40) {
            INSS = salariobruto * 0.12 - 78.36;
        } else if (salariobruto >= 3134.41 & salariobruto <= 6101.05) {
            INSS = salariobruto * 0.14 - 141.05;
        } else if (salariobruto >= 6101.06) {
            INSS = 713.10;
        }

        salariobase = salariobruto - INSS + (dependentes * 189.59);

        if (salariobase <= 1903.98) {
            IRRF = 0;
        } else if (salariobase >= 1903.99 & salariobase <= 2826.65) {
            IRRF = salariobase * 0.075 - 142.80;
        } else if (salariobase >= 2826.66 & salariobase <= 3751.05) {
            IRRF = salariobase * 0.15 - 354.80;
        } else if (salariobase >= 3751.06 & salariobase <= 4664.68) {
            IRRF = salariobase * 0.225 - 636.13;
        } else if (salariobase >= 4664.68) {
            IRRF = salariobase * 0.275 - 869.36;
        }

        valorliquido = salariobruto - INSS - IRRF;

        DecimalFormat fmt = new DecimalFormat("0.00");

        String total = fmt.format(valorliquido);
        String INSSdesconto = fmt.format(INSS);
        String IRRFdesconto = fmt.format(IRRF);

        String resultados = "Valor Liquído = R$:"+total;
        resultados += System.lineSeparator() +"INSS = R$:"+INSSdesconto;
        resultados += System.lineSeparator() +"IRRF = R$:"+IRRFdesconto;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Resultados");

        builder.setMessage(resultados);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        alerta = builder.create();
        alerta.show();}

    }

    public void limpar(View view) {

        salario.setText("");
        dep.setText("");
    }


}