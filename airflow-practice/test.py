import json
from datetime import timedelta

import airflow
from airflow import DAG
from airflow.operators.http_operator import SimpleHttpOperator
from airflow.operators.bash_operator import BashOperator

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': airflow.utils.dates.days_ago(2),
    'email': ['airflow@example.com'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

dag = DAG('example_http_operator_demo', default_args=default_args)

t1 = SimpleHttpOperator(
    task_id='get_op',
    method='GET',
    endpoint='',
    http_conn_id='http_local',
    data={},
    headers={},
    dag=dag,
)

t2 = BashOperator(
    task_id='print_date1',
    bash_command='date',
    dag=dag,
)

t3 = BashOperator(
    task_id='print_date2',
    bash_command='date',
    dag=dag,
)
t4 = BashOperator(
    task_id='print_date3',
    bash_command='date',
    dag=dag,
)
t5 = BashOperator(
    task_id='print_date4',
    bash_command='date',
    dag=dag,
)
t6 = BashOperator(
    task_id='print_date5',
    bash_command='date',
    dag=dag,
)
t1 >>  t2 >> t3 >> t4
t5 >> t2
t6 >> t2

