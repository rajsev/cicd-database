import groovy.json.JsonSlurper
def output = ""
def app_base_url = ""
names = sh(

    script: "git log -m -1 --name-only --first-parent --pretty='format:' \$(git rev-parse --short HEAD) | tr '\n' ' '",
    returnStdout: true,

 )
print(names)
new FileWriter("file_list.txt", true).with {
        write(names)
        flush()
}
listdbItems = names.tokenize(" ")
for (String name : listdbItems) {
    if (name.startsWith("bifrost") && name.endsWith("changelog.sql")){
    print("Processing"+ name)
    execdbtables(name)
    }

    if (name.startsWith("dev") && name.endsWith("changelog.sql")){
        print("Processing"+ name)
        execdbtables(name)
     }
}

def execdbtables(name){
        d = readFile "${name}"
        print(d)
}
