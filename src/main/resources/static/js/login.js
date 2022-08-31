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

    var ui = new firebaseui.auth.AuthUI(firebase.auth());

    // FirebaseUIの各種設定
    var uiConfig = {
      callbacks: {
        signInSuccessWithAuthResult: function () {
          firebase.auth().currentUser.getIdToken(true)
            .then(idToken => {
              axios.get('/auth', {
                headers: {
                  Authorization: `Bearer ${idToken}`,
                }
              }).then(res => {
                  window.location.href = '/customers' 
                }
              ).catch(res => {
                  window.location.href = '/error' 
              })
            }
          )
          return false;
        },
        uiShown: function () {
          document.getElementById('loader').style.display = 'none';
        }
      },
      signInOptions: [{
          provider: firebase.auth.EmailAuthProvider.PROVIDER_ID,
          disableSignUp: {status:true}
        }
      ],
      credentialHelper: firebaseui.auth.CredentialHelper.NONE,
    };
    ui.start('#firebaseui-auth-container', uiConfig);
  }
}
