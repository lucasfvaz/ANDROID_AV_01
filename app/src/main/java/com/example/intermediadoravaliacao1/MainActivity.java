package com.example.intermediadoravaliacao1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    final static int TELA_VENDA_COMPRA = 123;
    LinkedList<ContatoVeiculo> contatosVeiculos;

    LinkedList<ContatoVeiculo> listaOriginal;
    ContatoVeiculoAdapter adapter;
    ListView lista;

    int posSelecionada = -1;

    static boolean click = true;

    static int positionClick = 0;


    class ContatoVeiculoAdapter extends ArrayAdapter<ContatoVeiculo>{
        public ContatoVeiculoAdapter(){
            super(MainActivity.this,0,contatosVeiculos);
        }

        @Override
        public View getView(int pos, View recycled, ViewGroup grupo ){
            if (recycled == null){
                recycled = getLayoutInflater().inflate(
                        R.layout.list_buy_sales,null);
            }
             ContatoVeiculo contatoVeiculo = contatosVeiculos.get(pos);
            getTipoImage(contatoVeiculo, recycled);
            ((TextView) recycled.findViewById(R.id.txt_marcamodelo)).setText(contatoVeiculo.getModelo());
            ((TextView) recycled.findViewById(R.id.txt_valor)).setText(Double.toString(contatoVeiculo.getValor()));
            return recycled;
        }
    }

    public void getTipoImage(@NonNull ContatoVeiculo contatoVeiculo, View recycled){
        if(contatoVeiculo.getTipo().contains("C")){
            ((ImageView) recycled.findViewById(R.id.img_tipo)).setImageResource(R.drawable.caminhao);
        }
        if(contatoVeiculo.getTipo().contains("B")){
            ((ImageView) recycled.findViewById(R.id.img_tipo)).setImageResource(R.drawable.carro);
        }
        if(contatoVeiculo.getTipo().contains("A")){
            ((ImageView) recycled.findViewById(R.id.img_tipo)).setImageResource(R.drawable.moto);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContatoVeiculo  c1 = new ContatoVeiculo("lucas","lucasoares@alunos.utfpr.edu.br","(45)998597945",20000,"vw","gol","B",2007,"C");
        ContatoVeiculo  c2 = new ContatoVeiculo("diego","diego@alunos.utfpr.edu.br","(45)998597945",15000,"fiat","uno","B",2010,"V");
        ContatoVeiculo  c3 = new ContatoVeiculo("gabriel","gabriel@alunos.utfpr.edu.br","(45)998597945",10000,"honda","twister","A",2010,"V");
        contatosVeiculos = new LinkedList<>();
        contatosVeiculos.add(c1);
        contatosVeiculos.add(c2);
        contatosVeiculos.add(c3);
        listaOriginal = new LinkedList<>();
        listaOriginal.add(c1);
        listaOriginal.add(c2);
        listaOriginal.add(c3);
        adapter =  new ContatoVeiculoAdapter();
        lista = (ListView) findViewById(R.id.lista_compravenda);
        lista.setAdapter(adapter);
        lista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Aqui faco a selecao do item clicado para alterar
                if(adapterView.isClickable() == true && click ) {
                    adapterView.getChildAt(i).setBackgroundColor(Color.GRAY);
                    click = false;
                    positionClick = i;

                }else{
                    if(i == positionClick ) {
                        adapterView.getChildAt(i).setBackgroundColor(Color.WHITE);
                        click = true;
                    }else{
                        adapterView.getChildAt(positionClick).setBackgroundColor(Color.WHITE);
                        adapterView.getChildAt(i).setBackgroundColor(Color.GRAY);
                        click = false;
                        positionClick = i;
                    }
                }
                adapter.notifyDataSetChanged();

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {


                View tela = getLayoutInflater().inflate(R.layout.dialig_info, null);

                TextView edNomeView =  tela.findViewById(R.id.txt_infonome);
                TextView edEmailView = tela.findViewById(R.id.txt_infoemail);
                TextView  edTelefoneView = tela.findViewById(R.id.txt_infotelefone);
                TextView edValorView   = tela.findViewById(R.id.txt_infovalor);
                TextView edMarcaView = tela.findViewById(R.id.txt_infomarca);
                TextView edModeloView = tela.findViewById(R.id.txt_infomodelo);
                TextView edAnoView = tela.findViewById(R.id.txt_infoano);
                TextView edTipoView = tela.findViewById(R.id.txt_infotipoveiculo);
                TextView edSituacaoView = tela.findViewById(R.id.txt_infosituacao);


                edNomeView.setText(listaOriginal.get(pos).getNome());
                edEmailView.setText(listaOriginal.get(pos).getEmail());
                edEmailView.setText(listaOriginal.get(pos).getEmail());
                edTelefoneView.setText(listaOriginal.get(pos).getTelefone());
                edValorView.setText(Double.toString(listaOriginal.get(pos).getValor()));
                edMarcaView.setText(listaOriginal.get(pos).getMarca());
                edModeloView.setText(listaOriginal.get(pos).getModelo());
                edAnoView.setText(Integer.toString(listaOriginal.get(pos).getAno()));
                if(listaOriginal.get(pos).getTipo().equals("A")){
                    edTipoView.setText("Moto");
                }
                if(listaOriginal.get(pos).getTipo().equals("C")){
                    edTipoView.setText("Caminhao");
                }
                if(listaOriginal.get(pos).getTipo().equals("B")){
                    edTipoView.setText("Carro");
                }
                if(listaOriginal.get(pos).getVendendocomprando().equals("C")){
                    edSituacaoView.setText("Comprando");
                }
                if(listaOriginal.get(pos).getVendendocomprando().equals("V")){
                    edSituacaoView.setText("Vendendo");
                }
                bld.setView(tela);

                bld.show();

                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }



    public void restaurarListaOriginal(View v) {
        contatosVeiculos.clear(); // Limpa a lista atual
        contatosVeiculos.addAll(listaOriginal); // Restaura os dados originais
        adapter.notifyDataSetChanged(); // Notifica o adaptador sobre as mudanças na lista
    }
    public void seanunciar(MenuItem m3) {
        Intent it = new Intent(this, TelaContatoVeiculo.class);
        startActivityForResult(it, TELA_VENDA_COMPRA);

    }

    public void remover(MenuItem m1) {
        final int pos = lista.getCheckedItemPosition();
        if (pos == -1) {
            Toast.makeText(this, "Selecione o  contato de  Veiculo para remover", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setTitle("Confirmacao");
            bld.setMessage("Deseja realmente remover este contato de Veiculo ?");
            bld.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    contatosVeiculos.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });
            bld.setNegativeButton("Nao", null);
            bld.show();
        }
    }
    public void editar(MenuItem m2) {
        final int pos = lista.getCheckedItemPosition();
        if (pos == -1) {
            Toast.makeText(this, "Selecione o  contato de  Veiculo para editar", Toast.LENGTH_SHORT).show();
        } else {
            posSelecionada = pos;
            Intent it = new Intent(MainActivity.this, TelaContatoVeiculo.class);
            it.putExtra("contatoEdicao", contatosVeiculos.get(pos));
            startActivityForResult(it, TELA_VENDA_COMPRA);
        }
    }


    public void dialogofiltrar(View v){
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle("Filtrar");

        View tela = getLayoutInflater().inflate(R.layout.dialogo_filter, null);

        bld.setView(tela);

        bld.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    EditText edf_marca = tela.findViewById(R.id.edf_marca);
                    EditText edf_anomn = tela.findViewById(R.id.edf_anominimo);
                    EditText edf_anomax = tela.findViewById(R.id.edf_anomaximo);
                    EditText edf_valormn = tela.findViewById(R.id.edf_valorminimo);
                    EditText edf_valormax = tela.findViewById(R.id.edf_valormaximo);
                    RadioButton rdf_carro = tela.findViewById(R.id.rbf_carro);
                    RadioButton rdf_moto = tela.findViewById(R.id.rbf_moto);
                    RadioButton rdf_caminhao = tela.findViewById(R.id.rbf_caminhao);
                    RadioButton rdf_comprando = tela.findViewById(R.id.rbf_compra);
                    RadioButton rdf_vendendo = tela.findViewById(R.id.rbf_venda);
                    String marcaFiltro = edf_marca.getText().toString();

                    // Converter entradas de texto para int (ano) e double (valor)
                    int anoMinimo = edf_anomn.getText().toString().equals("") ? 0 : Integer.parseInt(edf_anomn.getText().toString());
                    int anoMaximo = edf_anomax.getText().toString().equals("") ? 0 : Integer.parseInt(edf_anomax.getText().toString());
                    double valorMinimo = edf_valormn.getText().toString().equals("") ? 0 : Double.parseDouble(edf_valormn.getText().toString());
                    double valorMaximo = edf_valormax.getText().toString().equals("") ? 0 : Double.parseDouble(edf_valormax.getText().toString());

                    LinkedList<ContatoVeiculo> itensFiltrados = new LinkedList<>();

                    for (ContatoVeiculo contato : listaOriginal) {
                        if (contato.getMarca().equalsIgnoreCase(marcaFiltro) && !marcaFiltro.equals("")) {
                            // Filtro por marca
                            itensFiltrados.add(contato);
                        }

                        // Filtro por ano
                        if(anoMinimo != 0 && anoMaximo != 0) {
                            if (contato.getAno() >= anoMinimo && contato.getAno() <= anoMaximo ) {
                                itensFiltrados.add(contato);
                            }
                        }
                        // Filtro por valor
                        if (contato.getValor() >= valorMinimo && contato.getValor() <= valorMaximo &&
                        !(edf_valormn.getText().toString().equals("")) && !(edf_valormax.getText().toString().equals(""))) {
                            itensFiltrados.add(contato);
                        }
                        if(rdf_carro.isChecked() == true){
                            if(contato.getTipo().equalsIgnoreCase("B")){
                                itensFiltrados.add(contato);
                            }
                        }
                        if(rdf_moto.isChecked() == true){
                            if(contato.getTipo().equalsIgnoreCase("A")){
                                itensFiltrados.add(contato);
                            }
                        }
                        if(rdf_caminhao.isChecked() == true){
                            if(contato.getTipo().equalsIgnoreCase("C")){
                                itensFiltrados.add(contato);
                            }
                        }
                        if(rdf_comprando.isChecked() == true){
                            if(contato.getVendendocomprando().equalsIgnoreCase("C")){
                                itensFiltrados.add(contato);
                            }
                        }
                        if(rdf_vendendo.isChecked() == true){
                            if(contato.getVendendocomprando().equalsIgnoreCase("V")){
                                itensFiltrados.add(contato);
                            }
                        }
                    }

                    // Limpe a lista atual e adicione os itens filtrados
                    contatosVeiculos.clear();
                    contatosVeiculos.addAll(itensFiltrados);

                    // Notifique o adaptador sobre as mudanças na lista
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        bld.setNegativeButton("Cancelar", null);
        bld.setView(tela);
        bld.show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_inicial,menu);
        return true;
    }

    public void onActivityResult(int codigo, int resultado, Intent dados) {
        super.onActivityResult(codigo, resultado, dados);
        if (codigo == TELA_VENDA_COMPRA && resultado == RESULT_OK) {
            ContatoVeiculo contatoVeiculo = (ContatoVeiculo) dados.getSerializableExtra("contatoVeiculo");
            if (posSelecionada >= 0) {
                contatosVeiculos.set(posSelecionada, contatoVeiculo);
                posSelecionada = -1;
            } else {
                contatosVeiculos.add(contatoVeiculo);
                listaOriginal.add(contatoVeiculo);
            }

            adapter.notifyDataSetChanged();
        }
    }
}