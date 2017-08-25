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
