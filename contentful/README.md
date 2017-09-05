# Contentful <=> GraphQL Integration Server

https://node-project-starter-ngemfazjwo.now.sh/

### deving

```
npm i 
npm start
```

### setup

```
export TMHAS_CONTENTFUL_SPACE_ID=...
export process.env.TMHAS_CONTENTFUL_CDA_TOKEN=...
export process.env.THMAS_CONTENTFUL_CMA_TOKEN=...
```

### deploy

#### local
`now`

#### prod
`now -e THMAS_CONTENTFUL_CMA_TOKEN=${THMAS_CONTENTFUL_CMA_TOKEN} -e TMHAS_CONTENTFUL_CDA_TOKEN=${TMHAS_CONTENTFUL_CDA_TOKEN}`

