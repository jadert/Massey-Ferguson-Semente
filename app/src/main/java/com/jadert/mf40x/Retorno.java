package com.jadert.mf40x;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.activity.EdgeToEdge;

public class Retorno extends AppCompatActivity {
    private static final double VALOR_MF = 0.1953125;

    private static final String[][] ENGRENAGENS = {
            {"14", "14"}, {"14", "16"}, {"14", "18"}, {"14", "20"}, {"14", "22"},
            {"16", "14"}, {"16", "16"}, {"16", "18"}, {"16", "20"}, {"16", "22"},
            {"18", "14"}, {"18", "16"}, {"18", "18"}, {"18", "20"}, {"18", "22"},
            {"20", "14"}, {"20", "16"}, {"20", "18"}, {"20", "20"}, {"20", "22"},
            {"22", "14"}, {"22", "16"}, {"22", "18"}, {"22", "20"}, {"22", "22"}
    };

    private static final double[] RELACOES = {
            1.0, 0.875, 0.7777777777777778, 0.7, 0.6363636363636364,
            1.1428571428571428, 1.0, 0.8888888888888888, 0.8, 0.7272727272727273,
            1.2857142857142858, 1.125, 1.0, 0.9, 0.8181818181818182,
            1.4285714285714286, 1.25, 1.1111111111111112, 1.0, 0.9090909090909091,
            1.5714285714285714, 1.375, 1.2222222222222223, 1.1, 1.0
    };

    // --- Classe interna para organizar os dados ---
    private static class Combinacao {
        final double sementesPorMetro;
        final String eixoE, eixoF;

        Combinacao(double sementes, String a, String b) {
            this.sementesPorMetro = sementes;
            this.eixoE = a;
            this.eixoF = b;
        }
    }

    // --- Variáveis de UI ---
    private TextView resultadoSementesTextView;
    private TextView resultadoEixoE, resultadoEixoF;
    private SwitchCompat switchAcimaAbaixo;
    private Combinacao resultadoAbaixo, resultadoAcima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_retorno);
        // --- Referências da UI ---
        resultadoSementesTextView = findViewById(R.id.resultadoSementes);
        resultadoEixoE = findViewById(R.id.resultadoEixoE);
        resultadoEixoF = findViewById(R.id.resultadoEixoF);
        switchAcimaAbaixo = findViewById(R.id.switchAcimaAbaixo);
        FloatingActionButton voltar = findViewById(R.id.voltar);

        // --- Obter dados da Intent ---
        Intent intent = getIntent();
        int sementesAlvo = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("sementes")));
        int furos = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("furos")));

        // --- Calcular e encontrar os resultados ---
        calcularResultados(sementesAlvo, furos);

        // --- Configurar listeners ---
        switchAcimaAbaixo.setOnCheckedChangeListener((buttonView, isChecked) -> updateUI());
        voltar.setOnClickListener(view -> openActivity());

        // --- Exibir o resultado inicial ---
        updateUI();
    }

    private void calcularResultados(int sementesAlvo, int furos) {
        List<Combinacao> todasCombinacoes = gerarTodasCombinacoes(furos);

        // Encontrar os melhores resultados
        double maxAbaixo = -1, minAcima = Double.MAX_VALUE;

        for (Combinacao c : todasCombinacoes) {
            if (c.sementesPorMetro < sementesAlvo && c.sementesPorMetro > maxAbaixo) {
                maxAbaixo = c.sementesPorMetro;
                resultadoAbaixo = c;
            }
            if (c.sementesPorMetro > sementesAlvo && c.sementesPorMetro < minAcima) {
                minAcima = c.sementesPorMetro;
                resultadoAcima = c;
            }
        }
    }

    private List<Combinacao> gerarTodasCombinacoes(int furos) {
        List<Combinacao> combinacoes = new ArrayList<>();

        for (int i = 0; i < RELACOES.length; i++) {
            double sementesCalc = furos * (RELACOES[i] * VALOR_MF);
            Combinacao c = new Combinacao(sementesCalc, ENGRENAGENS[i][0], ENGRENAGENS[i][1]);
            combinacoes.add(c);
        }

        return combinacoes;
    }

    private void updateUI() {
        Combinacao resultadoFinal = getCombinacao();
        // Define o texto do resultado principal (sementes)
        resultadoSementesTextView.setText(getString(R.string.resultado_final_sementes, resultadoFinal.sementesPorMetro));

        // Define o texto para cada eixo individualmente
        resultadoEixoE.setText(resultadoFinal.eixoE);
        resultadoEixoF.setText(resultadoFinal.eixoF);
    }

    private Combinacao getCombinacao() {
        boolean isAcima = switchAcimaAbaixo.isChecked();
        if (!isAcima) return resultadoAbaixo;  // Abaixo
        return resultadoAcima;                 // Acima
    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}