package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: StuDemo
 * @description:
 * @author:
 * @create: 2020-06-28 17:40
 * @Modified By:
 * @Version: 1.0
 **/
public class wirtedemo {
    public static void main(String[] args) throws IOException {

        String str = "!DOCTYPE html>\n" +
                "<!-- saved from url=(0057)https://shopee.com.my/lingsenmaoyi.my?page=0&sortBy=sales -->\n" +
                "<html lang=\"zh-Hans\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  \n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\">\n" +
                "  <meta name=\"shopee:git-sha\" content=\"2e11838e534d4ee1f64fadeae0c2b67873e0ee50\">\n" +
                "  <meta name=\"shopee:version\" content=\"pc-v4.45.1\">\n" +
                "  <script src=\"./xixi_files/lp.js\" async=\"\"></script><script type=\"text/javascript\" async=\"\" src=\"./xixi_files/linkid.js\"></script><script type=\"text/javascript\" async=\"\" src=\"./xixi_files/analytics.js\"></script><script src=\"./xixi_files/757894527666013\" async=\"\"></script><script async=\"\" src=\"./xixi_files/fbevents.js\"></script><script async=\"\" src=\"./xixi_files/firebase-performance-standalone.js\"></script><script async=\"\" src=\"./xixi_files/gtm.js\"></script><script data-webfe=\"config\">\n" +
                "    window.__PAGE_ID__ = 1;\n" +
                "    window.__LOCALE__ = 'MY';\n" +
                "    window.__ENV__ = 'live';\n" +
                "    window.__ASSETS__ = {\"CCMS_FEATURE_TOGGLE\":{\"ADDON_DEAL\":{\"default\":true,\"MY\":false,\"TH\":false},\"ADDRESS_GEOLOCATION_SELECTOR\":{\"TH\":true,\"default\":false,\"VN\":true,\"BR\":true,\"IN\":true,\"PH\":true,\"MY\":true,\"ID\":true},\"ADDRESS_SELECT_ZIPCODE\":{\"default\":false,\"ID\":true,\"TH\":true},\"AMEX_CC\":{\"default\":false,\"TW\":false,\"VN\":true,\"IN\":true,\"SG\":true,\"ID\":true},\"BRAND_LIST\":{\"default\":true,\"BR\":false,\"IN\":false},\"BUNDLE_DEAL_USE_11\":{\"default\":true},\"DAILY_DISCOVER_TABS\":{\"default\":false,\"MY\":true,\"ID\":true,\"BR\":true},\"DEFAULT_RATING_FIVE\":{\"default\":false,\"MY\":true,\"VN\":true,\"BR\":true,\"IN\":true,\"PH\":true,\"SG\":true},\"DISTRIBUTE_CHAT_AGENT\":{\"default\":false},\"EDIT_ORDER_ADDRESS\":{\"default\":false,\"TW\":true},\"FILTER_LOCATION_PHASE_2\":{\"default\":false,\"ID\":true,\"VN\":true},\"FILTER_LOWEST_PRICE_GUARANTEE\":{\"default\":true,\"TW\":false},\"FILTER_SERVICE_BY_SHOPEE\":{\"default\":false,\"TW\":true,\"ID\":true},\"GROUP_BUY\":{\"default\":false,\"TW\":true},\"HIDE_PREFER_FILTER_IN_SORTING\":{\"default\":false,\"TW\":true,\"ID\":true,\"IN\":true},\"HIDE_RR_ITEMS_ON_RATING_MODAL\":{\"default\":false,\"SG\":true,\"MY\":true,\"TW\":true},\"ITEM_CARD_LOCATION\":{\"default\":true,\"PH\":false,\"SG\":false,\"BR\":false,\"IN\":false},\"ITEM_LIST_ADULT_CHECK\":{\"default\":false,\"TW\":true,\"ID\":true},\"JCB_CC\":{\"default\":false,\"TW\":true,\"ID\":true,\"VN\":true,\"TH\":true},\"KYC_CONSENT\":{\"default\":false,\"TH\":true},\"LANGUAGE_SELECTION\":{\"default\":false,\"MY\":true,\"TH\":true,\"IN\":true},\"NEW_PC_WEBCHAT\":{\"default\":true},\"OFFICIAL_MALL_BRAND_LABEL\":{\"default\":true,\"MY\":false,\"BR\":false,\"IN\":false},\"OFFICIAL_MALL_NAME_CHANGE\":{\"default\":true,\"PH\":false,\"TH\":false,\"BR\":false,\"IN\":false},\"ORDER_DETAIL_ERECEIPT\":{\"default\":false,\"TW\":true},\"ORDER_ITEM_DISPLAY_RETURN_POLICY\":{\"default\":true,\"PH\":false,\"TH\":false},\"ROBOTO_FONT\":{\"default\":false,\"MY\":true,\"BR\":true,\"IN\":true,\"PH\":true,\"SG\":true,\"ID\":true},\"SEARCHPAGE_MYADS_ENTRANCE\":{\"default\":false,\"TW\":true,\"ID\":true,\"VN\":true},\"SHOPEE_BUDDY\":{\"default\":false,\"TH\":true},\"SLASH_PRICE\":{\"default\":false,\"TW\":true,\"SG\":true,\"ID\":true},\"THREE_PL_MASKING\":{\"default\":false,\"VN\":true,\"TH\":true,\"PH\":true,\"MY\":true,\"ID\":true},\"TWO_TIER_SHIPPING_FEE_LEARN_MORE\":{\"default\":false,\"ID\":true},\"TW_KYC_POPUP_WEB\":{\"default\":false,\"TW\":true},\"VERIFY_CC_BEFORE_ADD\":{\"default\":true,\"MY\":false,\"VN\":false,\"BR\":false,\"IN\":false,\"SG\":false},\"VOUCHER_128\":{\"default\":false,\"MY\":true,\"ID\":true,\"VN\":true,\"TH\":true},\"VOUCHER_REMOVE_MASCOT\":{\"default\":true,\"TW\":false},\"WELCOME_PACKAGE\":{\"default\":true}},\"PAGE_MANIFEST\":{},\"SPECIAL_SHOP\":{\"data\":[{\"username\":\"taiwancollection.my\",\"shopid\":39014963},{\"username\":\"nyxmy\",\"shopid\":60906768},{\"username\":\"digiofficialstore\",\"shopid\":31231999},{\"username\":\"hlksuperstore\",\"shopid\":1788100},{\"username\":\"senheng.my\",\"shopid\":12482371},{\"username\":\"philips.os\",\"shopid\":10210584},{\"username\":\"levimy.os\",\"shopid\":21657241},{\"username\":\"oppo_fbs\",\"shopid\":98716705},{\"username\":\"fossil.os\",\"shopid\":93161366},{\"username\":\"sp_setia.os\",\"shopid\":120549371},{\"username\":\"carlorino.os\",\"shopid\":126606645},{\"username\":\"calvinklein.os\",\"shopid\":130155319},{\"username\":\"bonia.os\",\"shopid\":129417963},{\"username\":\"shopeewelcomegift\",\"shopid\":140994925},{\"username\":\"ugreen_official\",\"shopid\":82080415},{\"username\":\"ugreen.os\",\"shopid\":64923440},{\"username\":\"soldmore.my\",\"shopid\":53912706},{\"username\":\"nuest.my\",\"shopid\":53572675},{\"username\":\"mena.my\",\"shopid\":64374390},{\"username\":\"mitpslocal.my\",\"shopid\":62568488},{\"username\":\"942188408.my\",\"shopid\":32712682},{\"username\":\"lookfantastic.my\",\"shopid\":82417884},{\"username\":\"shoppingaddicts.my\",\"shopid\":61954464},{\"username\":\"fastsmart.my\",\"shopid\":90384761},{\"username\":\"cansheng.my\",\"shopid\":48959264},{\"username\":\"firewind.my\",\"shopid\":80019806},{\"username\":\"buygoodgoods.my\",\"shopid\":5979138},{\"username\":\"rainsky.my\",\"shopid\":64087742},{\"username\":\"weststreet.my\",\"shopid\":64086115},{\"username\":\"skmeiwatch_.my\",\"shopid\":89714150},{\"username\":\"cofoeofficial.my\",\"shopid\":105052889},{\"username\":\"cofoemedical.os\",\"shopid\":16586050},{\"username\":\"yibai.my\",\"shopid\":62694474},{\"username\":\"onebuycart.my\",\"shopid\":12165912},{\"username\":\"garniermy\",\"shopid\":55790034},{\"username\":\"maybellinemy\",\"shopid\":21656722},{\"username\":\"shopeeoneornot\",\"shopid\":147468512},{\"username\":\"peeps.os\",\"shopid\":170380371},{\"username\":\"selens_photo.my\",\"shopid\":161674919},{\"username\":\"selens.os\",\"shopid\":12854871},{\"username\":\"samsungmalaysia.os\",\"shopid\":131102790},{\"username\":\"dellastella.os\",\"shopid\":170380273},{\"username\":\"carin.os\",\"shopid\":170380135},{\"username\":\"skmei.os\",\"shopid\":83591286},{\"username\":\"sasa.os\",\"shopid\":193873314},{\"username\":\"cosmede.my\",\"shopid\":17838900},{\"username\":\"upspring.os\",\"shopid\":201399010},{\"username\":\"g2000.os\",\"shopid\":180289060},{\"username\":\"ellewatch.os\",\"shopid\":165250320},{\"username\":\"6ixty8ight.os\",\"shopid\":212419048},{\"username\":\"fashion_elements.my\",\"shopid\":203127147},{\"username\":\"87106547.my\",\"shopid\":148723384},{\"username\":\"chengzimo.my\",\"shopid\":147373749},{\"username\":\"3ccam.my\",\"shopid\":196251488},{\"username\":\"larocheposay.my\",\"shopid\":216926703},{\"username\":\"hponlinestore\",\"shopid\":28894571},{\"username\":\"maserati.os\",\"shopid\":227065637},{\"username\":\"lorealmy\",\"shopid\":55788683},{\"username\":\"morellato.os\",\"shopid\":120371615},{\"username\":\"ouime.os\",\"shopid\":227066077},{\"username\":\"pumawatch.os\",\"shopid\":141722916},{\"username\":\"amori.os\",\"shopid\":131430105},{\"username\":\"moodboardshoes.os\",\"shopid\":206442012},{\"username\":\"xiaocong866.my\",\"shopid\":165565109},{\"username\":\"iacmobile.my\",\"shopid\":233346421},{\"username\":\"converse.os\",\"shopid\":252277880}]},\"TRANSIFY_COLLECTION_MAPPING\":{\"en-live-7\":1593056807,\"vi-live-7\":1593056826,\"ms-my-live-7\":1593056848,\"th-live-7\":1593056906,\"zh-hans-live-7\":1593056885,\"id-live-7\":1593067538,\"zh-hant-live-7\":1593056866,\"pt-br-live-7\":1593056942,\"hi-live-7\":1593056960},\"TRANSIFY_MAPPING\":{\"id-live\":1593143989,\"vi-live\":1593143892,\"en-live\":1593143872,\"hi-live\":1593144028,\"zh-hant-live\":1593143930,\"th-live\":1593143968,\"ms-my-live\":1593143912,\"zh-hans-live\":1593143950,\"pt-br-live\":1593144009}};\n" +
                "  </script>\n" +
                "\n" +
                "  <script>function showBody(){document&&document.body&&(document.body.style.visibility=\"visible\")}var SHORT_URL_MAX_LENGTH=256,pathname=location&&location.pathname;if(\"/\"!==pathname&&pathname.length<SHORT_URL_MAX_LENGTH&&\"\"===location.hash&&-1===pathname.indexOf(\"-\")&&0===pathname.lastIndexOf(\"/\")){document&&document.body&&(document.body.style.visibility=\"hidden\"),setTimeout(showBody,5e3);var xhr=new XMLHttpRequest;xhr.open(\"GET\",\"/api/v0/is_short_url/?path=\"+pathname.replace(\"/\",\"\")),xhr.setRequestHeader(\"Content-Type\",\"application/json\"),xhr.setRequestHeader(\"Accept\",\"application/json\"),xhr.onreadystatechange=function(){if(4===this.readyState)if(200===this.status)if(JSON.parse(this.responseText).error)showBody();else{var e=document.createElement(\"a\");e.href=location.href,e.search+=\"?\"===e.search[0]?\"&__classic__=1\":\"?__classic__=1\",location.href=e.href}else showBody()},xhr.send()}</script>\n" +
                "  <script>if(window.ga=window.ga||function(){(ga.q=ga.q||[]).push(arguments)},ga.l=+new Date,window.PerformanceObserver){var observer=new window.PerformanceObserver(function(e){for(var r=e.getEntries(),n=0;n<r.length;n++){var a=r[n],i=a.name,t=Math.round(a.startTime+a.duration);ga(\"send\",{hitType:\"timing\",timingCategory:\"Performance Metrics\",timingVar:i,timingValue:t})}});observer.observe({entryTypes:[\"paint\"]})}</script>\n" +
                "  <script>window.prometheusGetChunks=function(){return window.webpackJsonp};</script>\n" +
                "  <!-- Open search -->\n" +
                "  \n" +
                "  \n" +
                "    <link type=\"application/opensearchdescription+xml\" rel=\"search\" href=\"https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/assets/6aedf25aea23b47d2f06deccb1a9e740.xml\">\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "    <link rel=\"dns-prefetch\" href=\"https://cf.shopee.com.my/\">\n" +
                "  \n" +
                "    <link rel=\"dns-prefetch\" href=\"https://deo.shopeemobile.com/shopee/\">\n" +
                "  \n" +
                "    <link rel=\"dns-prefetch\" href=\"https://cv.shopee.com.my/\">\n" +
                "  \n" +
                "  <script>dataLayer = [];</script>\n" +
                "  <script>(function (w, d, s, l, i) {\n" +
                "      w[l] = w[l] || []; w[l].push({\n" +
                "        'gtm.start':\n" +
                "          new Date().getTime(), event: 'gtm.js', country: 'my'\n" +
                "      }); var f = d.getElementsByTagName(s)[0],\n" +
                "        j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : ''; j.async = true; j.src =\n" +
                "          'https://www.googletagmanager.com/gtm.js?id=' + i + dl; f.parentNode.insertBefore(j, f);\n" +
                "    })(window, document, 'script', 'dataLayer', 'GTM-WJZQSJF');</script>\n" +
                "\n" +
                "  \n" +
                "  <script>!function(c,f){if(self&&performance){var t,o,i,e=[],r={passive:!0,capture:!0},n=new Date,a=\"pointerup\",u=\"pointercancel\";m(c),self.perfMetrics=self.perfMetrics||{},self.perfMetrics.onFirstInputDelay=function(n){e.push(n),s()}}function p(n,e){t||(t=e,o=n,i=new Date,m(f),s())}function s(){0<=o&&o<i-n&&(e.forEach(function(n){n(o,t)}),e=[])}function l(n){if(n.cancelable){var e=(1e12<n.timeStamp?new Date:performance.now())-n.timeStamp;\"pointerdown\"===n.type?function(n,e){function t(){p(n,e),i()}function o(){i()}function i(){f(a,t,r),f(u,o,r)}c(a,t,r),c(u,o,r)}(e,n):p(e,n)}}function m(e){[\"click\",\"mousedown\",\"keydown\",\"touchstart\",\"pointerdown\"].forEach(function(n){e(n,l,r)})}}(addEventListener,removeEventListener);</script>\n" +
                "  <script>function shopeeInitFirebase(e){var n=document.createElement(\"script\"),a=!1,i=!1;n.async=1,n.src=\"https://www.gstatic.com/firebasejs/6.5.0/firebase-performance-standalone.js\";var t=document.getElementsByTagName(\"script\")[0];function r(){a&&i&&window.firebase&&(window._globalFirebasePerformanceEntry=window.firebase.initializeApp(e).performance())}t.parentNode.insertBefore(n,t),window.addEventListener(\"load\",function(){i=!0,r()}),n.onload=function(){a=!0,r()}}</script>\n" +
                "  <script>\n" +
                "    shopeeInitFirebase({\"appId\":\"1:808332928752:web:3453c91b768a9e3e\",\"apiKey\":\"AIzaSyCZnRmQRNJOw3NFAfJsyKixBYoN61P1MYQ\",\"authDomain\":\"shopee-ad86f.firebaseapp.com\",\"databaseURL\":\"https://shopee-ad86f.firebaseio.com\",\"projectId\":\"shopee-ad86f\",\"storageBucket\":\"shopee-ad86f.appspot.com\",\"messagingSenderId\":\"808332928752\"});\n" +
                "  </script>";
        String strarr[] = str.split("href=\"h");

        for(String a : strarr) {
            String fileName = "/Users/maxin/Desktop/test.txt";
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(a.getBytes());
            fos.write("========".getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        }
    }
}
