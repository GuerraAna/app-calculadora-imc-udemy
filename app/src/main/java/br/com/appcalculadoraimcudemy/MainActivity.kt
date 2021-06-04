package br.com.appcalculadoraimcudemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.appcalculadoraimcudemy.databinding.ActivityMainBinding
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonCalc = binding.btCalcularImc

        buttonCalc.setOnClickListener {
            val editPeso = binding.etPeso.text.toString()
            val editAltura = binding.etAltura.text.toString()

            if ((editPeso.isEmpty())){
                Toast.makeText(this, "Insira o Peso", Toast.LENGTH_SHORT).show()
            } else if (editAltura.isEmpty()) {
                Toast.makeText(this, "Insira a Altura", Toast.LENGTH_SHORT).show()
            } else {
                CalculoImc()
            }
        }
    }
    private fun CalculoImc() {
        val peso = binding.etPeso
        val altura = binding.etAltura
        val resposta = binding.resultado

        val pesoIMC = Integer.parseInt(peso.text.toString())
        val alturaIMC = parseFloat(altura.text.toString())

        val imc = pesoIMC / (alturaIMC * alturaIMC)

        val texto = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade Grau 1"
            imc <= 39.9 -> "Obesidade Grau 2"
            else -> "Obesidade Morbida"
        }
        imc.toString()
        resposta.setText("IMC: $imc \n $texto")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_top_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.reset -> {
                val limparEditPeso = binding.etPeso
                val limparEditAltura = binding.etAltura
                val limparMensagem = binding.resultado

                limparMensagem.setText("")
                limparEditAltura.setText("")
                limparEditPeso.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
