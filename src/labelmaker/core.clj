(ns labelmaker.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [hiccup.page :as hiccup-page]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit "label.html"
        (str (hiccup-page/html5
              [:head
               [:style (slurp (io/resource "labelmaker.css"))]]
              [:title "Labels"]
              [:body
               [:p "Hello, World!"]]))))
