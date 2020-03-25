# Terraform scripts

``` hcl
##
## Creates a Dynamodb table for Optimus Service
##

resource "aws_dynamodb_table" "optimus-kv" {
  name         = "OptimusKV"

  billing_mode   = "PAY_PER_REQUEST"
  ## billing_mode   = "PROVISIONED"
  ## read_capacity  = "${var.read_capacity}"
  ## write_capacity = "${var.write_capacity}"

  hash_key  = "id"

  # enable Point-in-time Recovery
  point_in_time_recovery {
    enabled = true
  }

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "__version"
    type = "S"
  }

  global_secondary_index {
    name               = "VersionIndex"
    hash_key           = "__version"
    projection_type    = "KEYS_ONLY"
  }


  tags = {
    Name        = "OptimusKV"
    application = "Optimus"
  }
}


resource "aws_dynamodb_table" "optimus-metastore" {
  name         = "OptimusMetadataStore"

  billing_mode   = "PAY_PER_REQUEST"
  ## billing_mode   = "PROVISIONED"
  ## read_capacity  = "${var.read_capacity}"
  ## write_capacity = "${var.write_capacity}"

  hash_key  = "id"

  # enable Point-in-time Recovery
  point_in_time_recovery {
    enabled = true
  }

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "__dataset"
    type = "S"
  }

  attribute {
    name = "__entryType"
    type = "S"
  }

  global_secondary_index {
    name               = "MetaTypeIndex"
    hash_key           = "__entryType"
    range_key          = "__dataset"
    projection_type    = "ALL"
  }

  tags = {
    Name        = "OptimusMetadataStore"
    application = "Optimus"
  }
}


resource "aws_dynamodb_table" "optimus-taskq" {
  name         = "OptimusTasksQ"

  billing_mode  = "PAY_PER_REQUEST"
  ## billing_mode   = "PROVISIONED"
  ## read_capacity  = "${var.read_capacity}"
  ## write_capacity = "${var.write_capacity}"

  hash_key  = "id"

  # enable Point-in-time Recovery
  point_in_time_recovery {
    enabled = true
  }

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "topic"
    type = "S"
  }

  attribute {
    name = "__reserv_key"
    type = "S"
  }

  global_secondary_index {
    name               = "reservation-index"
    hash_key           = "topic"
    range_key          = "__reserv_key"
    projection_type    = "ALL"
  }

  tags = {
    Name        = "OptimusTasksQ"
    application = "Optimus"
  }
}

```
