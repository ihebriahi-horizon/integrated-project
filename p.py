import base64

data = """{
    "auths":{
        "ghcr.io":{
            "auth": "d2Fzc2VmOTExOmdocF9Jc0hxMHptVmh5ZkQxazNubFhhMlhaczlaVGRkUG8zYTZITnU="
        }
    }
}
"""

encodedBytes = base64.b64encode(data.encode("utf-8"))
encodedStr = str(encodedBytes, "utf-8")

print(encodedStr)