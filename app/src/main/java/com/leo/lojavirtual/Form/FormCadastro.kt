package com.leo.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.leo.lojavirtual.R
import com.leo.lojavirtual.databinding.ActivityFormCadastroBinding

private lateinit var binding: ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()

        binding.btCadastrar.setOnClickListener {
            CadastrarUsuario()
        }
    }

    private fun CadastrarUsuario(){

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isEmpty() || senha.isEmpty()){

            var snackbar = Snackbar.make(binding.layoutCad,"Coloque o seu e-mail e sua senha!",Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setAction("✔", View.OnClickListener {
                })
            snackbar.show()
        }else{

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {

                if (it.isSuccessful){
                    var snackbar = Snackbar.make(binding.layoutCad,"Cadastro realizado com Sucesso!",Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.BLACK)
                        .setAction("✔", View.OnClickListener {
                            VoltarFormLogin()
                        })
                    snackbar.show()

                }
            }.addOnFailureListener {
                var snackbar = Snackbar.make(binding.layoutCad,"Erro ao cadastrar usuário",Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLACK)
                    .setAction("✔", View.OnClickListener {
                    })
                snackbar.show()
            }
        }
    }
    private fun VoltarFormLogin(){
         var intent = Intent(this,FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}