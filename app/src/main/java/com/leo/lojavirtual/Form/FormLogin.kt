package com.leo.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.leo.lojavirtual.R
import com.leo.lojavirtual.Telaprincipal
import com.leo.lojavirtual.databinding.ActivityFormLoginBinding

private lateinit var binding:ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        VerificarUsarioLogado()

        supportActionBar!!.hide()

        binding.textTelacad.setOnClickListener {
            intent = Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {
            AutenticarUsuario()
        }

    }
    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isEmpty() || senha.isEmpty()){

            var snackbar = Snackbar.make(binding.layoutLogin,"Coloque seu email e senha",Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                .setAction("Ok", View.OnClickListener {

                })
            snackbar.show()
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {

                if (it.isSuccessful){
                    binding.frameL.visibility= View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({AbrirTelaPrincipal()},3000)
                }
            }.addOnFailureListener {
                var snackbar = Snackbar.make(binding.layoutLogin,"Erro ao logar usuário!",Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                    .setAction("Ok", View.OnClickListener {

                    })
                snackbar.show()
            }
        }
    }

    private fun VerificarUsarioLogado(){

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual!=null){
            AbrirTelaPrincipal()
        }
    }


    private fun AbrirTelaPrincipal(){
        var intent = Intent(this,Telaprincipal::class.java)
        startActivity(intent)
        finish()
    }

}