terraform {
  backend "s3" {
    bucket  = "horizon-tfstate"
    key     = "horizon.tfstate"
    region  = "eu-west-1"
    encrypt = true
  }
}

provider "aws" {
  region = "eu-west-1"
}

resource "aws_s3_bucket_versioning" "enabled" {
  bucket = aws_s3_bucket.terraform_state.id
  versioning_configuration {
    status = "Enabled"
  }
}

module "datalake-production" {
  source     = "./modules/data-lake"
  bucketname = "datalake.horizon.com"
}


resource "aws_s3_bucket" "static-content-bucket" {
  bucket = "static.horizon.com"
  acl    = "private"
  policy = file("policies/static.horizon.com.policy.json")
  website {
    index_document = "index.html"
    error_document = "error.html"
  }
  tags = {
    Name        = "horizon"
    Environment = "production"
  }
}
