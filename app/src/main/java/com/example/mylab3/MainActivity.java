package com.example.mylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Головна активність застосунку, яка відповідає за логіку баскетбольного табло.
 * Вона керує відображенням рахунку, обробляє натискання кнопок та зберігає стан
 * при зміні конфігурації (наприклад, при зміні теми чи орієнтації).
 */
public class MainActivity extends AppCompatActivity {

    // Ключі для збереження стану (для onSaveInstanceState/onCreate)
    static final String STATE_SCORE_TEAM_A = "scoreTeamA";
    static final String STATE_SCORE_TEAM_B = "scoreTeamB";

    // Змінна для зберігання поточного рахунку Команди А (Україна)
    int scoreTeamA = 0;
    // Змінна для зберігання поточного рахунку Команди В (Іспанія)
    int scoreTeamB = 0;

    /**
     * Викликається при першому створенні активності або при відновленні стану.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Вмикає режим EdgeToEdge, дозволяючи вмісту займати весь простір екрана.
        EdgeToEdge.enable(this);
        // Встановлює макет активності з файлу activity_main.xml
        setContentView(R.layout.activity_main);

        // --- ЛОГІКА ВІДНОВЛЕННЯ СТАНУ ---
        // savedInstanceState не буде null, якщо активність відновлюється (наприклад, після зміни орієнтації або теми)
        if (savedInstanceState != null) {
            // Відновлюємо рахунок Команди А, використовуючи раніше збережений ключ.
            scoreTeamA = savedInstanceState.getInt(STATE_SCORE_TEAM_A, 0);
            // Відновлюємо рахунок Команди В.
            scoreTeamB = savedInstanceState.getInt(STATE_SCORE_TEAM_B, 0);
        }
        // --------------------------------

        // Налаштовує обробку системних відступів (insets).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.score_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        // Ініціалізуємо відображення рахунків (відображаються або 0, або відновлені значення)
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Викликається системою перед тим, як активність буде знищена (наприклад, при зміні орієнтації).
     * Використовується для збереження динамічного стану інтерфейсу.
     * @param outState Пакет, у якому зберігається стан.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Зберігаємо поточний рахунок Команди А
        outState.putInt(STATE_SCORE_TEAM_A, scoreTeamA);
        // Зберігаємо поточний рахунок Команди В
        outState.putInt(STATE_SCORE_TEAM_B, scoreTeamB);
        // Завжди викликайте реалізацію суперкласу.
        super.onSaveInstanceState(outState);
    }

    // ---------------------- Методи для Команди А (Україна) ----------------------

    /**
     * Додає 1 очко до рахунку Команди А (Вільний кидок).
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Додає 2 очки до рахунку Команди А.
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Додає 3 очки до рахунку Команди А.
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    // ---------------------- Методи для Команди В (Іспанія) ----------------------

    /**
     * Додає 1 очко до рахунку Команди В (Вільний кидок).
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Додає 2 очки до рахунку Команди В.
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Додає 3 очки до рахунку Команди В.
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    // ---------------------- Методи відображення ----------------------

    /**
     * Оновлює TextView, яке відображає рахунок Команди А.
     * Знаходить елемент за ідентифікатором R.id.team_a_score.
     * @param score Нове значення рахунку для відображення.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Оновлює TextView, яке відображає рахунок Команди В.
     * Знаходить елемент за ідентифікатором R.id.team_b_score.
     * @param score Нове значення рахунку для відображення.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    // ---------------------- Метод скидання ----------------------

    /**
     * Скидає рахунок обох команд до 0 і оновлює відображення на екрані.
     * Викликається при натисканні на кнопку "Скинути рахунок".
     * @param v Поточний View (кнопка), що викликала метод.
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        // Оновлюємо відображення
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}