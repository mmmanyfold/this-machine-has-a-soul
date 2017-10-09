# Contentful <=> GraphQL Integration Server

https://this-machine-has-a-soul-jppxccuwtc.now.sh/about

https://this-machine-has-a-soul-jppxccuwtc.now.sh/media

https://this-machine-has-a-soul-jppxccuwtc.now.sh/people

### deving

```
npm i 
npm start
```

### setup

```
export TMHAS_CONTENTFUL_SPACE_ID=...
export TMHAS_CONTENTFUL_CDA_TOKEN=...
export THMAS_CONTENTFUL_CMA_TOKEN=...
```

### deploy

#### local
`now`

#### prod
`now -e THMAS_CONTENTFUL_CMA_TOKEN=${THMAS_CONTENTFUL_CMA_TOKEN} -e TMHAS_CONTENTFUL_CDA_TOKEN=${TMHAS_CONTENTFUL_CDA_TOKEN}`

