package com.example.intermediadoravaliacao1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaContatoVeiculo extends AppCompatActivity {

   TextView edNome,edEmail,edTelefone,edValor,edMarca,edModelo,edAno;
   RadioButton rbCaminhao, rbMoto, rbCarro ,rbCompra, rbVenda;

    String edTipo;
    String edVendendoComprando;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato_veiculo);
        edNome = (EditText) findViewById(R.id.ed_nome);
        edEmail = (EditText) findViewById(R.id.ed_email);
        edTelefone = (EditText) findViewById(R.id.ed_telefone);
        edValor   = (EditText) findViewById(R.id.ed_valor);
        edMarca = (EditText) findViewById(R.id.ed_marca);
        edModelo   = (EditText) findViewById(R.id.ed_modelo);
        edAno  = (EditText) findViewById(R.id.ed_ano);
        rbCaminhao = (RadioButton)findViewById(R.id.caminhao);
        rbMoto= (RadioButton)findViewById(R.id.moto);
        rbCarro= (RadioButton)findViewById(R.id.carro);
        rbCompra = (RadioButton) findViewById(R.id.compra);
        rbVenda = (RadioButton) findViewById(R.id.venda);



        ContatoVeiculo c = (ContatoVeiculo) getIntent().getSerializableExtra("contatoEdicao");
        if(c != null){
            edNome.setText(c.getNome());
            edEmail.setText(c.getEmail());
            edTelefone.setText(c.getTelefone());
            edValor.setText(Double.toString(c.getValor()));
            edMarca.setText(c.getMarca());
            edModelo.setText(c.getModelo());
            edAno.setText(Integer.toString(c.getAno()));
            if(c.getVendendocomprando().contains("C")){
                rbCompra.setChecked(true);
            }
            if(c.getVendendocomprando().contains("V")){
                rbVenda.setChecked(true);
            }
            if(c.getTipo().contains("C")){
                rbCaminhao.setChecked(true);
            }
            if(c.getTipo().contains("B")){
                rbCarro.setChecked(true);
            }
            if(c.getTipo().contains("A")){
                rbMoto.setChecked(true);
            }
            edValor.setText(Double.toString(c.getValor()));

        }
    }


    public void confirmar(View v){
        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String telefone = edTelefone.getText().toString();
        Double valor = Double.parseDouble(edValor.getText().toString());
        String marca = edMarca.getText().toString();
        String modelo = edModelo.getText().toString();
        Integer ano = Integer.parseInt(edAno.getText().toString());
        if(rbCompra.isChecked() == true){
            edVendendoComprando = "C";
        }
        if(rbVenda.isChecked() == true){
            edVendendoComprando = "V";
        }
        if(rbCaminhao.isChecked() == true){
            edTipo = "C";
        }
        if(rbCarro.isChecked() == true){
            edTipo = "B";
        }
        if(rbMoto.isChecked() == true){
            edTipo = "A";
        }
        ContatoVeiculo contatoVeiculo = new ContatoVeiculo();

        contatoVeiculo.setNome(nome);
        contatoVeiculo.setEmail(email);
        contatoVeiculo.setTelefone(telefone);
        contatoVeiculo.setValor(valor);
        contatoVeiculo.setMarca(marca);
        contatoVeiculo.setModelo(modelo);
        contatoVeiculo.setAno(ano);
        contatoVeiculo.setTipo(edTipo);
        contatoVeiculo.setVendendocomprando(edVendendoComprando);
        Intent dados  = new Intent();

        dados.putExtra("contatoVeiculo",contatoVeiculo);
        setResult(RESULT_OK,dados);
        v.setBackgroundColor(Color.WHITE);
        finish();
    }

}
