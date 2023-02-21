
(cl:in-package :asdf)

(defsystem "ydlidar_ros_driver-msg"
  :depends-on (:roslisp-msg-protocol :roslisp-utils :std_msgs-msg
)
  :components ((:file "_package")
    (:file "LaserFan" :depends-on ("_package_LaserFan"))
    (:file "_package_LaserFan" :depends-on ("_package"))
  ))