# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.16

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/ubuntu/ros_serial/src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/ubuntu/ros_serial/build

# Include any dependencies generated for this target.
include serial_demo/CMakeFiles/serial_demo.dir/depend.make

# Include the progress variables for this target.
include serial_demo/CMakeFiles/serial_demo.dir/progress.make

# Include the compile flags for this target's objects.
include serial_demo/CMakeFiles/serial_demo.dir/flags.make

serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o: serial_demo/CMakeFiles/serial_demo.dir/flags.make
serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o: /home/ubuntu/ros_serial/src/serial_demo/src/serial_demo.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ubuntu/ros_serial/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o"
	cd /home/ubuntu/ros_serial/build/serial_demo && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o -c /home/ubuntu/ros_serial/src/serial_demo/src/serial_demo.cpp

serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/serial_demo.dir/src/serial_demo.cpp.i"
	cd /home/ubuntu/ros_serial/build/serial_demo && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/ubuntu/ros_serial/src/serial_demo/src/serial_demo.cpp > CMakeFiles/serial_demo.dir/src/serial_demo.cpp.i

serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/serial_demo.dir/src/serial_demo.cpp.s"
	cd /home/ubuntu/ros_serial/build/serial_demo && /usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/ubuntu/ros_serial/src/serial_demo/src/serial_demo.cpp -o CMakeFiles/serial_demo.dir/src/serial_demo.cpp.s

# Object files for target serial_demo
serial_demo_OBJECTS = \
"CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o"

# External object files for target serial_demo
serial_demo_EXTERNAL_OBJECTS =

/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: serial_demo/CMakeFiles/serial_demo.dir/src/serial_demo.cpp.o
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: serial_demo/CMakeFiles/serial_demo.dir/build.make
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/libroscpp.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libpthread.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_chrono.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_filesystem.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/librosconsole.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/librosconsole_log4cxx.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/librosconsole_backend_interface.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/liblog4cxx.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_regex.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/libroscpp_serialization.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/libxmlrpcpp.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/librostime.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_date_time.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/libcpp_common.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_system.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libboost_thread.so.1.71.0
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /usr/lib/aarch64-linux-gnu/libconsole_bridge.so.0.4
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: /opt/ros/noetic/lib/libserial.so
/home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo: serial_demo/CMakeFiles/serial_demo.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/ubuntu/ros_serial/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable /home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo"
	cd /home/ubuntu/ros_serial/build/serial_demo && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/serial_demo.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
serial_demo/CMakeFiles/serial_demo.dir/build: /home/ubuntu/ros_serial/devel/lib/serial_demo/serial_demo

.PHONY : serial_demo/CMakeFiles/serial_demo.dir/build

serial_demo/CMakeFiles/serial_demo.dir/clean:
	cd /home/ubuntu/ros_serial/build/serial_demo && $(CMAKE_COMMAND) -P CMakeFiles/serial_demo.dir/cmake_clean.cmake
.PHONY : serial_demo/CMakeFiles/serial_demo.dir/clean

serial_demo/CMakeFiles/serial_demo.dir/depend:
	cd /home/ubuntu/ros_serial/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/ubuntu/ros_serial/src /home/ubuntu/ros_serial/src/serial_demo /home/ubuntu/ros_serial/build /home/ubuntu/ros_serial/build/serial_demo /home/ubuntu/ros_serial/build/serial_demo/CMakeFiles/serial_demo.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : serial_demo/CMakeFiles/serial_demo.dir/depend

