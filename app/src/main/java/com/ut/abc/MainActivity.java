package com.ut.abc;

import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etName = findViewById(R.id.etName);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String fileName = etName.getText().toString().trim();

            try {
                InputStream in = getAssets().open(fileName);


                File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                OutputStream out = new FileOutputStream(new File(outDir, fileName));


                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                // Đóng luồng
                in.close();
                out.close();

                Toast.makeText(this, "Thành công!", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Lỗi: File không tồn tại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}