(ns dippi.core
  (:require [clj-http.client :as client]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

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

  - artefacts"
  [database search-type search-by]
  (slurp-json (str "http://data.nhm.ac.uk/api/action/datastore_search?" database search-type search-by)))

(defn query-nhm-api
  "Expects a name of a database (see http://data.nhm.ac.uk/dataset?author=Natural+History+Museum)
   and a query string i.e \"Archaeopteryx\""
  [database query]
  (search-nhm-api database "&q=" query))

(defn filter-nhm-api
  "Expects a name of a database (see http://data.nhm.ac.uk/dataset?author=Natural+History+Museum)
   and a filter field and search string i.e \"catalogNumber\" \"PV P 51007\""
  [database field query]
  (let [filter-by (str "{\"" field "\":\"" query "\"}")]
    (search-nhm-api database "&filters=" filter-by)))

;; 145 potential fields, each with a :type (i.e. text) and an :id (a string, that is a keyword
;; in the :records

(def collections
  {:artefacts "resource_id=ec61d82a-748d-4b53-8e99-3e708e76bc4d"
   :specimens "resource_id=05ff2255-c38a-40c9-b657-4ccb55ab2feb"
   :former-bp "resource_id=a5f98284-8c19-4636-ae24-01b60cb6b9a4"
   :index-lot "resource_id=bb909597-dedf-427d-8c04-4c02b3a24db3"
   :interactions-bank "resource_id=9f49865f-ca81-488e-8738-82c569fb562e"})


(def cli-options
  [["-d" "--database DATABASE" "Database name"]
   ["-q" "--query QUERY" "Search query"]
   ["-f" "--field FIELD" "Search field for filtering"]
   ["-n" "--number NUMBER" "Returns count of records"]])

(defn -main [& args]
  (let [{:keys [options arguments summary]} (parse-opts args cli-options)
        {:keys [database query field number]} options
        db ((keyword database) collections)]
    (if (empty? field)
      (if (empty? number)
        (prn (:records (query-nhm-api db query)))
        (prn (str "Number of records: " (:total (query-nhm-api db query)))))
      (if (empty? number)
        (prn (:records (filter-nhm-api db field query)))
        (prn (str "Number of records: " (:total (filter-nhm-api db field query))))))))
