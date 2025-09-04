export const environment = {
  production: false,
  authConfig: {
    issuer: 'http://localhost:8080',
    responseType: 'code',
    clientId: 'client',
    scope: 'openid',
    redirectUri: 'http://localhost:4200/authorized',
    requireHttps: false,
    strictDiscoveryDocumentValidation: false,
    showDebugInformation: true,
    skipIssuerCheck: true
  }
};
