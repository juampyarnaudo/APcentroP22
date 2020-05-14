package com.jparnaudo.apcentro22.fragments

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.jparnaudo.apcentro22.R
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_contacto.*
import kotlinx.android.synthetic.main.fragment_contacto.view.*

class ContactoFragment : Fragment(), RewardedVideoAdListener {
    private lateinit var mRewardedVideoAd: RewardedVideoAd
    private var listener: OnFragmentActionsListener? = null
    private val numberPipos = 5493804535559
    private val numberAndy = 5493804514861
    private val textoWhats = "Hola cómo estás? tengo una consulta para hacerte..."
    private val ubicacionGym = "https://www.google.com/maps/dir/-29.4167112,-66.8533726/Av.+Monse%C3%B1or+E.+Angelelli+1545-1655,+F5300+La+Rioja/@-29.4241319,-66.8589599,15z/data=!3m1!4b1!4m10!4m9!1m1!4e1!1m5!1m1!1s0x9427da29711d59af:0x13df14ee541f563f!2m2!1d-66.8489674!2d-29.4316079!3e2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_contacto, container, false)
        // Inflate the layout for this fragment
        MobileAds.initialize(activity, "ca-app-pub-3517612946554837~6240767962")
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()

        view.ivWhatsappPiposca.setOnClickListener { view ->
            val url2 = "https://wa.me/$numberPipos/?text=$textoWhats"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url2)
            startActivity(i)
        }
        view.ivWhatsappAndy.setOnClickListener { view ->
            val url2 = "https://wa.me/$numberAndy/?text=$textoWhats"
            val uri = Uri.parse(url2)
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.whatsapp.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url2)
                    )
                )
            }

        }

        view.animation_view1.setOnClickListener {
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }
        view.animation_view2.setOnClickListener {
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }


        view.ivInstagramGym.setOnClickListener { view ->
            val uri = Uri.parse("http://instagram.com/_u/p22centrodeentrenamiento")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/p22centrodeentrenamiento")
                    )
                )
            }

        }
        view.ivInstagramPiposca.setOnClickListener { view ->
            val uri = Uri.parse("https://instagram.com/molinaoscar_piposca")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://instagram.com/molinaoscar_piposca")
                    )
                )
            }

        }
        view.ivInstagramAndy.setOnClickListener { view ->
            val uri = Uri.parse("https://instagram.com/cerkvenihandres")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.instagram.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://instagram.com/cerkvenihandres")
                    )
                )
            }

        }

        view.ivUbicacion.setOnClickListener { view ->
            val uri = Uri.parse(ubicacionGym)
            val likeIng = Intent(Intent.ACTION_VIEW, uri)
            likeIng.setPackage("com.maps.android")
            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(ubicacionGym)
                    )
                )
            }

        }
        return view
    }
    private fun loadRewardedVideoAd() {
//        var prueba = "ca-app-pub-3940256099942544/5224354917"
        var micodigo = "ca-app-pub-3517612946554837/7362277945"
        mRewardedVideoAd.loadAd(micodigo,
            AdRequest.Builder().build())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        btnPlus.setOnClickListener { listener?.onClickFragmentButton() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(activity, "Muchas gracias por tu aporte!",Toast.LENGTH_SHORT).show()
        // Reward the user.
    }

    override fun onRewardedVideoAdLeftApplication() {
//        Toast.makeText(activity, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
//        Toast.makeText(activity, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
//        Toast.makeText(activity, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdLoaded() {
//        Toast.makeText(activity, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdOpened() {
//        Toast.makeText(activity, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
//        Toast.makeText(activity, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoCompleted() {
//        Toast.makeText(activity, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(activity)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(activity)
    }




}
