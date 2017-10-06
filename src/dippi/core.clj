(ns dippi.core
  (:require [clj-http.client :as client]))

(defn slurp-json
  "Returns the contents of the url pointing to a json."
  [url]
  (let [response (client/get url {:as :json
                                  :throw-exceptions false})]
    (if (= 200 (:status response))
      (get-in response [:body :result])
      nil)))

(defn search-nhm-api
  "Takes a database, search type and string to search by.
  Possible databases are

  - collection-specimens

  - artefacts

  "
  [database search-type search-by]
  (slurp-json (str "http://data.nhm.ac.uk/api/action/datastore_search?" database search-type search-by)))

(defn query-nhm-api
  "Expects a name of a database (see http://data.nhm.ac.uk/dataset?author=Natural+History+Museum)
   and a query string i.e \"Archaeopteryx\""
  [database query]
  (-> (search-nhm-api database "&q=" query)
      :records))

(defn filter-nhm-api
  "Expects a name of a database (see http://data.nhm.ac.uk/dataset?author=Natural+History+Museum)
   and a filter field and search string i.e \"catalogNumber\" \"PV P 51007\""
  [database field query]
  (let [filter-by (str "{\"" field "\":\"" query "\"}")]
    (search-nhm-api database "&filters=" filter-by)))

(def collection-specimens "resource_id=05ff2255-c38a-40c9-b657-4ccb55ab2feb")

(def artefacts "resource_id=ec61d82a-748d-4b53-8e99-3e708e76bc4d")

(def former-bp "resource_id=a5f98284-8c19-4636-ae24-01b60cb6b9a4")

(def index-lot "resource_id=bb909597-dedf-427d-8c04-4c02b3a24db3")

(def interactions-bank "resource_id=9f49865f-ca81-488e-8738-82c569fb562e")

(comment  "&q=Archaeopteryx"

          "&limit=5"

          "&filters={\"catalogNumber\":\"PV P 51007\"}")

;; a query can contain multiple records (# recorded in :total), which are stored in :records

;; 145 potential fields, each with a :type (i.e. text) and an :id (a string, that is a keyword
;; in the :records
