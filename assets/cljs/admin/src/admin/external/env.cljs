(ns admin.external.env
  )


#_(Defines a compile time value that the build can override during
    compilation. Defaults to the dev config, shown below.
    )
(goog-define server "")

(def config
  { :server server
   
   })

