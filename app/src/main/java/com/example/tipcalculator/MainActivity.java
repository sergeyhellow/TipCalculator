package com.example.tipcalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextOrderAmount;
    private SeekBar seekBarTipPercentage;
    private TextView textViewTipPercentage, textViewOrderTotal, textViewTipAmount, textViewTotalWithTip;
    private CheckBox checkBoxDish1, checkBoxDish2, checkBoxDish3, checkBoxDish4, checkBoxDish5;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим элементы интерфейса
        editTextOrderAmount = findViewById(R.id.editTextOrderAmount);
        seekBarTipPercentage = findViewById(R.id.seekBarTipPercentage);
        textViewTipPercentage = findViewById(R.id.textViewTipPercentage);
        checkBoxDish1 = findViewById(R.id.checkBoxDish1);
        checkBoxDish2 = findViewById(R.id.checkBoxDish2);
        checkBoxDish3 = findViewById(R.id.checkBoxDish3);
        checkBoxDish4 = findViewById(R.id.checkBoxDish4);
        checkBoxDish5 = findViewById(R.id.checkBoxDish5);
        textViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        textViewTipAmount = findViewById(R.id.textViewTipAmount);
        textViewTotalWithTip = findViewById(R.id.textViewTotalWithTip);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        // Установка слушателя для SeekBar
        seekBarTipPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewTipPercentage.setText("Процент чаевых: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Установка слушателя для кнопки расчёта
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {

        String orderAmountStr = editTextOrderAmount.getText().toString();

        // Проверяем, не является ли строка пустой
        if (orderAmountStr.isEmpty()) {
            Toast.makeText(this, "Введите сумму заказа", Toast.LENGTH_SHORT).show();
            return; // Выходим из метода, если строка пустая
        }

        double orderAmount = Double.parseDouble(orderAmountStr);
        int totalDishesCost = 0; // Стоимость блюд
        if (checkBoxDish1.isChecked()) totalDishesCost += 100;
        if (checkBoxDish2.isChecked()) totalDishesCost += 100;
        if (checkBoxDish3.isChecked()) totalDishesCost += 100;
        if (checkBoxDish4.isChecked()) totalDishesCost += 100;
        if (checkBoxDish5.isChecked()) totalDishesCost += 100;

        double tipPercentage = seekBarTipPercentage.getProgress(); // Процент чаевых
        orderAmount = Double.parseDouble(editTextOrderAmount.getText().toString()); // Сумма заказа
        double tipAmount = (orderAmount + totalDishesCost) * (tipPercentage / 100); // Сумма чаевых
        double totalWithTip = orderAmount + totalDishesCost + tipAmount; // Общая сумма с чаевыми

        textViewOrderTotal.setText(String.format("Сумма заказа: %.2f руб.", orderAmount + totalDishesCost));
        textViewTipAmount.setText(String.format("Сумма чаевых: %.2f руб.", tipAmount));
        textViewTotalWithTip.setText(String.format("Общая сумма с чаевыми: %.2f руб.", totalWithTip));
    }
}
