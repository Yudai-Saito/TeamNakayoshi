class Index {

  static init() {
    const config = {
      apiKey: "AIzaSyDO1OsQp21pKGqR24lvAzdh3hA1P9yTn6U",
      authDomain: "teamnakayoshi.firebaseapp.com",
      projectId: "teamnakayoshi",
      storageBucket: "teamnakayoshi.appspot.com",
      messagingSenderId: "211099684937",
      appId: "1:211099684937:web:efa22e75696df663952f98"
    };

    firebase.initializeApp(config);

    // FirebaseUIインスタンス初期化
    var ui = new firebaseui.auth.AuthUI(firebase.auth());

    // FirebaseUIの各種設定
    var uiConfig = {
      callbacks: {
        signInSuccessWithAuthResult: function () {
          // サインイン成功時のコールバック関数
          // 戻り値で自動的にリダイレクトするかどうかを指定
          firebase.auth().currentUser.getIdToken(true)
            .then(idToken => {
              axios.get('/auth', {
                headers: {
                  Authorization: `Bearer ${idToken}`,
                }
              }).then(res => {
                  window.location.href = '/main' 
                }
              ).catch(res => {
                  window.location.href = '/error' 
              })
            }
          )
          return false;
        },
        uiShown: function () {
          // FirebaseUIウィジェット描画完了時のコールバック関数
          // 読込中で表示しているローダー要素を消す
          document.getElementById('loader').style.display = 'none';
        }
      },
      signInOptions: [{
          // サポートするプロバイダを指定
          provider: firebase.auth.EmailAuthProvider.PROVIDER_ID,
          //未登録メールでの新規登録禁止
          disableSignUp: {status:true}
        }
      ],
      //アカウント選択を行う画面の防止
      credentialHelper: firebaseui.auth.CredentialHelper.NONE,
    };
    // FirebaseUI描画開始
    ui.start('#firebaseui-auth-container', uiConfig);
  }
}
