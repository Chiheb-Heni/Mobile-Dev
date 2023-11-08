import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.view.View;

import com.example.heni_chihebhosni_mesure_glycemie.R;

public class MainActivity extends AppCompatActivity {

    private SeekBar ageSeekBar;
    private TextView ageTextView;
    private RadioGroup jeuneRadioGroup;
    private Button calculerButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageSeekBar = ageSeekBar.findViewById();
        ageTextView = ageTextView.findViewById();
        jeuneRadioGroup = jeuneRadioGroup.findViewById();
        calculerButton = calculerButton(R.id.calculerButton);
        resultTextView = resultTextView(R.id.resultTextView);

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageTextView.setText((progress + 1) + " ans");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        calculerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = jeuneRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                boolean jeun = selectedRadioButton.getText().toString().equalsIgnoreCase("oui");
                int age = ageSeekBar.getProgress() + 1;

                String result = calculerNiveauGlycemie(age, jeun);
                resultTextView.setText(result);
            }
        });
    }

    private String calculerNiveauGlycemie(int age, boolean jeun) {
        double mesureGlycemie = 6.0; // Remplacez par la mesure réelle

        if (jeun) {
            if (age > 13) {
                if (mesureGlycemie >= 5.0 && mesureGlycemie <= 7.2) {
                    return "Niveau de glycémie est normal";
                } else if (mesureGlycemie < 5.0) {
                    return "Niveau de glycémie est trop bas";
                } else {
                    return "Niveau de glycémie est trop élevé";
                }
            } else if (age >= 6 && age <= 12) {
                if (mesureGlycemie >= 5.0 && mesureGlycemie <= 10.0) {
                    return "Niveau de glycémie est normal";
                } else if (mesureGlycemie < 5.0) {
                    return "Niveau de glycémie est trop bas";
                } else {
                    return "Niveau de glycémie est trop élevé";
                }
            } else {
                if (mesureGlycemie >= 5.5 && mesureGlycemie <= 10.0) {
                    return "Niveau de glycémie est normal";
                } else if (mesureGlycemie < 5.5) {
                    return "Niveau de glycémie est trop bas";
                } else {
                    return "Niveau de glycémie est trop élevé";
                }
            }
        } else {
            if (mesureGlycemie < 10.5) {
                return "Niveau de glycémie est normal";
            } else {
                return "Niveau de glycémie est trop élevé";
            }
        }
    }
}
