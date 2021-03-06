<?php
include 'connection.php';
$query = mysqli_query($con, "SELECT * FROM laundry");
$data = array();
$qry_array = array();
$i = 0;
$total = mysqli_num_rows($query);
while ($row = mysqli_fetch_array($query)) {
  $data['id'] = $row['id'];
  $data['uid'] = $row['uid'];
  $data['name'] = $row['name'];
  $data['phone'] = $row['phone'];
  $data['address'] = $row['address'];
  $data['city'] = $row['city'];
  $qry_array[$i] = $data;
  $i++;
}

if ($query) {
  $response['success'] = 'true';
  $response['message'] = 'Data Loaded Successfully';
  $response['total'] = $total;
  $response['data'] = $qry_array;
} else {
  $response['success'] = 'false';
  $response['message'] = 'Data Loading Failed';
}

echo json_encode($response);
