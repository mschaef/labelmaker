(ns labelmaker.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [hiccup.page :as hiccup-page]))

(defn normalize-label-definition [ definition ]
  (if (string? definition)
    {:title definition}
    definition))

(defn render-page [ & contents ]
  [:div.page contents])

(defn render-label [ contents ]
  (let [{title :title
         body :body
         class :class} (normalize-label-definition contents)]
    [:div.label
     [:div.body (when class {:class class})
      [:h1 title]
      (when body
        [:p body])]]))

(defn render-labels [ labels ]
  (map render-page
       (partition-all 2
                      (map render-label labels))))

(defn render-html-document [ page-contents ]
  (hiccup-page/html5
   [:head
    [:style (slurp (io/resource "labelmaker.css"))]]
   [:body
    page-contents])  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit "label.html"
        (str
         (render-html-document
          (render-labels (edn/read-string (slurp "labels.edn")))))))
