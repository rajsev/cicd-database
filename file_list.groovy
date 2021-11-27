import groovy.json.JsonSlurper
def output = ""
def app_base_url = ""
names = sh(

    script: "git log -m -1 --name-only --first-parent --pretty='format:' \$(git rev-parse --short HEAD) | tr '\n' ' '",
    returnStdout: true,

 )
print(names)

sh "git checkout master"

                    