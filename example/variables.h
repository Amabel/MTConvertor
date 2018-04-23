/* -*- mode:promela -*- */

/* グローバル変数 */
byte OS_status = E_OK;          /* 現在のOSの状態 */
TaskID runningTask = NoTaskID;  /* 現在RUNNINGのタスクID NoTaskID:実行中のタスクなし*/

Index decTask = 0;               /* Declareされているタスク数 */
Index decRes = 0;                /* Declareされているリソース数 */
Index decEvent = 0;              /* Declareされているイベント数 */

TCBType tcb[MAXTASK];
RCBType rcb[MAXRES];
ECBType ecb[MAXEVENT];
