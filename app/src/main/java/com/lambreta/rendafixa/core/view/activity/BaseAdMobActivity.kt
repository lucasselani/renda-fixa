package com.lambreta.rendafixa.core.view.activity

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.lambreta.rendafixa.BuildConfig
import com.lambreta.rendafixa.R

abstract class BaseAdMobActivity : BaseActivity() {

    private lateinit var ad: InterstitialAd

    protected fun startAds() {
        MobileAds.initialize(this, getString(R.string.admob_key))
        ad = InterstitialAd(this).apply {
            adUnitId = getString(
                if(BuildConfig.DEBUG) com.lambreta.rendafixa.R.string.admob_test_key
                else com.lambreta.rendafixa.R.string.admob_interstitial_key
            )
            adListener = adListener()
        }
        ad.loadAd(AdRequest.Builder().build())
    }

    private fun adListener() =
            object: AdListener() {
                override fun onAdLoaded() {
                    if(ad.isLoaded) ad.show()
                }
            }
}