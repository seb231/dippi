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

(def collection-specimens "resource_id=05ff2255-c38a-40c9-b657-4ccb55ab2feb")

(def artefacts "resource_id=ec61d82a-748d-4b53-8e99-3e708e76bc4d" )

