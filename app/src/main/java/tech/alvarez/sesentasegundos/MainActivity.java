package tech.alvarez.sesentasegundos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView contadorTextView;
    private FloatingActionButton iniciarButton;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contadorTextView = (TextView) findViewById(R.id.contadorTextView);
        iniciarButton = (FloatingActionButton) findViewById(R.id.iniciarButton);
        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarConteo();
            }
        });
    }

    private void iniciarConteo() {
        // TODO: Paso 5
        // Luego de colocar lo que hara el AsyncTask, lo iniciamos.
        ContadorTask contadorTask = new ContadorTask();
        contadorTask.execute(60);

    }

    // AsyncTask

    private class ContadorTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // TODO: Paso 1
            // Aquí se pone lo que quieres hacer antes de iniciar el proceso
            iniciarButton.setEnabled(false);


        }

        @Override
        protected Void doInBackground(Integer... integers) {

            // TODO: Paso 2
            // Aquí se pone lo que tarda tiempo (el proceso)
            int tope = integers[0];

            publishProgress(tope);

            try {
                do {
                    tope--;
                    Thread.sleep(100);
                    publishProgress(tope);
                } while (tope > 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // TODO: Paso 3
            // Aquí se pone si necesitas actualizar algo de la interfaz durante el proceso
            String numero = values[0].toString();

            contadorTextView.setText(numero);


        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // TODO: Paso 4
            // Aquí se pone lo que quieres hacer cuando termine el proceso
            iniciarButton.setEnabled(true);

            intent = new Intent(getApplicationContext(),SegundaActivity.class);
            startActivity(intent);
        }
    }
}
