package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoveExpenseActivity extends AppCompatActivity {

    private String baseURL = "http://192.168.1.31:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_remove_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View listView = findViewById(R.id.listView);
        List<Expense> expenseList = new ArrayList<>();

        APIExpense apiExpense = new APIExpense(getIntent().getStringExtra("accessToken"), getIntent().getStringExtra("refreshToken"));
        apiExpense.getExpenses(new ExpensesCallback() {
            @Override
            public void onExpenseRecieved(List<Expense> expenses) {
                expenseList.addAll(expenses);
                ExpenseAdapter adapter = new ExpenseAdapter(RemoveExpenseActivity.this, expenses);
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }

            @Override
            public void onExpenseError(Throwable t) {
                Log.d("API Expense", "Error: " + t.getMessage());
            }

        });
        ListView listViewSet = findViewById(R.id.listView);

        listViewSet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ExpenseAdapter adapter = new ExpenseAdapter(RemoveExpenseActivity.this, expenseList);
                Expense expenseToRemove = expenseList.get(position);
                expenseList.remove(position);
                adapter.notifyDataSetChanged();

                APIExpense apiExpense = new APIExpense(getIntent().getStringExtra("accessToken"), getIntent().getStringExtra("refreshToken"));
                apiExpense.deleteExpense(new DeleteExpenseCallback() {
                    @Override
                    public void onSuccess() {
                        Log.d("API Expense", "Successfully deleted expense");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.d("API Expense", "Failed to delete expense: " + errorMessage);
                    }
                }, expenseToRemove.getId());

                return true;
            }
        });

    }
}