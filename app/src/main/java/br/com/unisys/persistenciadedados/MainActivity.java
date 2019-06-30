package br.com.unisys.persistenciadedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;

    //Variavel definida como final, pois não poderá ser alterada no decorrer do projeto, ou seja, é uma constante.
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar); //Mapeando o botão salvar da view para a variável
        editNome = findViewById(R.id.editNome); //Mapeando editNome da view para a variável
        textResultado = findViewById(R.id.textResultado); //Mapeando o textResultado da view para a variável


        //Função que será executada quando o botão for acionado
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //O shared preferences cria um arquivo para salvar dados na memória interna do celular
                //O parâmetro ARQUIVO_PREFERENCIA é o nome do arquivo criado
                //O segundo parâmetro mode:0 que é o modo privado, que só o aplicativo que manipulará os dados do arquivo
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

                //O shared preferences editor retorna um objeto, é com ele que vamos editar o arquivo de preferência
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                //Caso o nome esteja vazio, vai pular uma msg na tela do usuário dizendo para Preencher o nome
                //getText pega os valores vindos da view e converte para String, o equals faz a comparação
                if(editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_LONG).show();
                }else{
                    //A variável nome será atribuida aos valores vindos da view
                    String nome = editNome.getText().toString();
                    //O putString é utilizado para salvar os dados préviamante
                    //O primeiro parâmetro é a chave utilizada
                    //O segundo parâmetro passamos o valor a ser salvo
                    editor.putString("nome", nome);
                    //O commit é responsável por salvar os dados
                    editor.commit();
                    //O valor nome será concatenado com a Strind Olá dentro da variável textResultado que será mostrado na view
                    textResultado.setText("Olá, "+ nome+"!");
                }
            }
        });

        //Recuperar os dados salvos
        //O shared preferences cria um arquivo para salvar dados na memória interna do celular
        //O parâmetro ARQUIVO_PREFERENCIA é o nome do arquivo criado
        //O segundo parâmetro mode:0 que é o modo privado, que só o aplicativo que manipulará os dados do arquivo
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Validar se temos algum valor em preferências
        //Preferences é um arquivo xml que contém os valores das chaves que você foi salvando
        //Contains é um método que irá verificar se existe o valor da chave que estamos solicitando
        if(preferences.contains("nome")){

            //A variável nome vai receber o valor da chave se houver no arquivo de preferencia
            //Se houver o nome, ela vai retornar o nome, caso não, irá retornar o valor default
            String nome = preferences.getString("nome","usuário não definido!");
            textResultado.setText("Olá, "+nome);

        }else{
            textResultado.setText("Olá, usuário não deficnido!");
        }



    }
}
