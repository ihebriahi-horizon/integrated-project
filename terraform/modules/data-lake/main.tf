variable "bucketname" {
  type = string
}

resource "aws_s3_bucket" "datalake-bucket" {
  bucket = var.bucketname
  acl    = "private"
  policy = file("policies/datalake.horizon.com.policy.json")
  tags = {
    Name        = var.bucketname
    Environment = "production"
  }
}

resource "aws_s3_bucket_public_access_block" "block-public-datalake" {
  bucket                  = aws_s3_bucket.datalake-bucket.id
  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}
