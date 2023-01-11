export const environment = {
  production: false,

  /* Authorization service url */
  loginUrl:"http://localhost:8080/api/auth/signin",
  registerUrl:"http://localhost:8080/api/auth/signup",
  newChangePasswordUrl:"http://localhost:8080/api/auth/change-password",
  newFailedAttemptUrl:"http://localhost:8080/api/auth/failed-attempt",

  /* Employee service url */
  baseUrl:"http://localhost:8080/api/employees",
  exportUrl : "http://localhost:8080/api/employees/export",

  /* User Service url */
  PublicUrl: "http://localhost:8080/api/test/all",
  UserUrl: "http://localhost:8080/api/test/user",
  AdminUrl: "http://localhost:8080/api/test/admin"
};


