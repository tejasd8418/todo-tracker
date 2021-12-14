// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  firebaseConfig : {
    apiKey: "AIzaSyCOnsNn2ybBJvRf9DU8qVG6xOuilQa53DM",
    authDomain: "todonotification-1.firebaseapp.com",
    projectId: "todonotification-1",
    storageBucket: "todonotification-1.appspot.com",
    messagingSenderId: "766900620214",
    appId: "1:766900620214:web:182105a28a6f9b55168f17",
    measurementId: "${config.measurementId}"
  },
  production: false
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
