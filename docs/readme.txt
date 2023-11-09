# consum query
curl 'https://tienda.consum.es/api/rest/V1.0/catalog/searcher/products?q=cerveza+estrella+damm&limit=20&showRecommendations=true' \
  -H 'sec-ch-ua: "Google Chrome";v="119", "Chromium";v="119", "Not?A_Brand";v="24"' \
  -H 'X-TOL-ZONE: 0' \
  -H 'X-TOL-CHANNEL: 1' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36' \
  -H 'X-TOL-LOCALE: es' \
  -H 'Accept: application/json, text/plain, */*' \
  -H 'X-TOL-CURRENCY: EUR' \
  -H 'Referer: https://tienda.consum.es/es' \
  -H 'sec-ch-ua-platform: "Linux"' \
  --compressed \
  | jq '[.catalog.products[] | {name: .productData.name, price: .priceData.prices[].value.centUnitAmount}]'

  | jq -r '[.catalog.products[].productData.name, .catalog.products[].priceData.prices[].value.centUnitAmount] | transpose | 
  | jq '.catalog.products[].productData.name, .catalog.products[].priceData.prices[].value.centUnitAmount'
  | jq '.catalog.products[].productData.name'
  | jq '.catalog.products[].priceData.prices[].value.centUnitAmount'  
  


#mercadon query
curl -X POST \
     -H "X-Algolia-API-Key: 9d8f2e39e90df472b4f2e559a116fe17" \
     -H "X-Algolia-Application-Id: 7UZJKL1DJ0" \
     --data-binary '{"params":"query=cerveza+estrella+damm&getRankingInfo=false"}' \
     "https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_bcn1_es/query" \
| jq '[.hits[] | {name: .display_name, price: .price_instructions.bulk_price}]'


| jq '.hits[].display_name'

| jq '.hits[].price_instructions.bulk_price'


#dia
curl 'https://www.dia.es/search?q=hamburguesa' \
  -H 'sec-ch-ua: "Google Chrome";v="119", "Chromium";v="119", "Not?A_Brand";v="24"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "Linux"' \
  -H 'sec-fetch-dest: document' \
  -H 'sec-fetch-mode: navigate' \
  -H 'user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36' \
  --compressed

#eroski
curl 'https://supermercado.eroski.es/ca/search/results/?q=aceite%20de%20oliva&suggestionsFilter=false' \
  -H 'authority: supermercado.eroski.es' \
  -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7' \
  -H 'accept-language: ca-ES,ca;q=0.9,es-ES;q=0.8,es;q=0.7' \
  -H 'cache-control: max-age=0' \
  -H 'user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36' \
  --compressed






  curl 'https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_bcn1_es/query?x-algolia-agent=Algolia%20for%20JavaScript%20(3.35.1)%3B%20Browser&x-algolia-application-id=7UZJKL1DJ0&x-algolia-api-key=9d8f2e39e90df472b4f2e559a116fe17' \
  -H 'Accept-Language: ca-ES,ca;q=0.9,es-ES;q=0.8,es;q=0.7' \
  -H 'Connection: keep-alive' \
  -H 'Origin: https://tienda.mercadona.es' \
  -H 'Referer: https://tienda.mercadona.es/' \
  -H 'Sec-Fetch-Dest: empty' \
  -H 'Sec-Fetch-Mode: cors' \
  -H 'Sec-Fetch-Site: cross-site' \
  -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36' \
  -H 'accept: application/json' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'sec-ch-ua: "Google Chrome";v="119", "Chromium";v="119", "Not?A_Brand";v="24"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "Linux"' \
  --data-raw '{"params":"query=garbanzos&clickAnalytics=true&analyticsTags=%5B%22web%22%5D&getRankingInfo=true"}' \
  --compressed




curl -X GET \
     -H "X-Algolia-API-Key: 9d8f2e39e90df472b4f2e559a116fe17" \
     -H "X-Algolia-Application-Id: 7UZJKL1DJ0" \
    "https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_bcn1_es/60836"


curl -X GET \
     -H "X-Algolia-API-Key: 9d8f2e39e90df472b4f2e559a116fe17" \
     -H "X-Algolia-Application-Id: 7UZJKL1DJ0" \
    "https://7uzjkl1dj0-dsn.algolia.net/1/keys/9d8f2e39e90df472b4f2e559a116fe17"


curl -X POST \
     -H "X-Algolia-API-Key: 9d8f2e39e90df472b4f2e559a116fe17" \
     -H "X-Algolia-Application-Id: 7UZJKL1DJ0" \
     --data '{}' \
    "https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_bcn1_es/browse"
