export const environment = {
  production: true,
  authConfig: {
    issuer: 'https://your-production-domain.com/auth/realms/your-realm',
    responseType: 'code',
    clientId: 'your-client-id',
    scope: 'openid profile email',
    redirectUri: 'https://your-production-domain.com/authorized',
    requireHttps: true,
    strictDiscoveryDocumentValidation: true,
    showDebugInformation: false,
    skipIssuerCheck: false
  }
};
