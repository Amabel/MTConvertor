/* -*- mode:promela -*- */
/* 定義 */
#define PRIORITY byte
#define TaskID byte
#define ResID byte
#define EventID byte
#define Mask int
#define Index byte

/* スレッドの状態 */
mtype = {RUNNING, READY, SUSPENDED, WAITING};

/* エラーコード */
#define E_OK 0
#define E_OS_ACCESS 1
#define E_OS_CALLEVEL 2
#define E_OS_ID 3
#define E_OS_LIMIT 4
#define E_OS_NOFUNC 5
#define E_OS_RESOURCE 6
#define E_OS_STATE 7
#define E_OS_VALUE 8

/* タスクの種類 */
#define BASIC 0                 /* 基本タスク */
#define EXTENDED 1              /* 拡張タスク */

#define InitTaskID (255)        /* 初期タスクID */
#define NoTaskID (255)          /* 実行中のタスクなし or 未宣言 */
#define NoResID (255)           /* 獲得中のリソースなし or 未宣言 */
#define NoEventID (255)         /* 未宣言 */
#define InvalidIndex (255)      /* 無効値 該当なし時のindex */

/* コンフィグ値 */
#define MAXPRI 9                /* 優先度の数 0~MAXPRI-1 */
#define FIFO_MAX_NUM 8          /* 優先度毎の各キューの要素数 0~FIFO_MAX_NUM-1*/
#define MAXTASK 4               /* TCBTypeの要素数（タスクの種類数）0~MAXTASK-1 */
#define MAXRES 4                /* RCBの要素数（リソースの種類数）0~MAXRES-1 */
#define MAXEVENT 4              /* ECBの要素数（イベントの種類数）0~MAXEVENT-1 */

/* リソース */
typedef RCBType{
  ResID res_id = NoResID;       /* ID */
  PRIORITY res_ceilpri = 0;     /* 優先度 */
  bool res_locked = false;      /* 獲得済みかどうか */
}
typedef ResStack{
  ResID resourceID;             /* 獲得したリソースのID */
  PRIORITY prev_pri;            /* 獲得前の優先度 */
}

/* イベント */
typedef ECBType{
  EventID eve_id = NoEventID; /* ID */
  Mask eve_mask = 0;          /* イベントマスク */
}

/* タスク */
typedef TCBType{
  TaskID t_id = NoTaskID;       /* ID */
  byte concept = BASIC;         /* タスクの種類 */
  PRIORITY t_init_pri = 0;      /* 初期優先度 */
  PRIORITY t_cur_pri = 0;       /* 現在優先度 0(low)~MAXPRI-1(high) */
  mtype t_state = SUSPENDED;    /* 状態 */
  bool t_preemptable = false;   /* プリエンプション有無 */
  byte maxActcnt = 0;           /* 最大多重起動数 */
  byte actcnt = 0;              /* 現在多重起動数 */

  ResStack occRes[MAXRES];      /* 獲得したリソースのスタック */
  byte numoccRes = 0;           /* スタックに格納されているリソース数 */

  Mask eve_wait = 0;            /* 待機イベント */
  Mask eve_set = 0;             /* セット済みイベント */
}
