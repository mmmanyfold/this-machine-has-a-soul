/**
 * Application configuration section
 * http://pm2.keymetrics.io/docs/usage/application-declaration/
 */
module.exports = {
    apps: [
        {
            name: 'run',
            script: './bin/run',
            env: {
                COMMON_VARIABLE: 'true'
            },
            env_production: {
                NODE_ENV: 'production',
                TMHAS_CONTENTFUL_SPACE_ID: "",
                TMHAS_CONTENTFUL_CDA_TOKEN: "",
                THMAS_CONTENTFUL_CMA_TOKEN: ""
            }
        }
    ]
};