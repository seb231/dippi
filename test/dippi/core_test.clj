(ns dippi.core-test
  (:require [clojure.test :refer :all]
            [dippi.core :refer :all]))

(deftest slurp-json-test
  "test NHM Data Portal response"
  (is (not (nil? (first (slurp-json "http://data.nhm.ac.uk/api/action/datastore_search?resource_id=05ff2255-c38a-40c9-b657-4ccb55ab2feb"))))))

;;; Original API tests came from R. Mounce (http://rossmounce.co.uk/2015/09/30/using-the-nhm-data-portal-api/)

(deftest query-nhm-api-test
  "test for Archaeopteryx query"
  (is (not (nil? (query-nhm-api collection-specimens "Archaeopteryx")))))

(deftest filter-nhm-api-test
  "test API filter function"
  (is (not (nil? (filter-nhm-api collection-specimens "catalogNumber" "PV P 51007")))))
